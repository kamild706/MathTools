package pl.n32.mathtools.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pl.n32.mathtools.Classes.Numbers;
import pl.n32.mathtools.R;
import pl.n32.mathtools.Validation;

public class Factors extends Fragment
{
    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_divisors, container, false);
        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new Calculate().execute();
            }
        });
        return rootView;
    }

    private void displayWarning()
    {
        Toast.makeText(getActivity(), R.string.cannot_process_that_value, Toast.LENGTH_SHORT).show();
    }

    private class Calculate extends AsyncTask<Boolean, Void, String>
    {
        private ProgressDialog progress;
        private String number;
        private long start;

        @Override
        protected void onPreExecute()
        {
            start = System.currentTimeMillis();

            progress = new ProgressDialog(getActivity());
            progress.setTitle(getString(R.string.calculating));
            progress.setMessage(getString(R.string.can_take_a_second));
            progress.setCancelable(false);
            progress.show();

            number = ((EditText) rootView.findViewById(R.id.number)).getText().toString();
        }

        @Override
        protected String doInBackground(Boolean... params)
        {
            if (!Validation.isLong(number))
                return "IV"; // invalid
            else if (Long.parseLong(number) < 1)
                return "IV";

            String[] result = Numbers.getFactorsOfNumber(Long.parseLong(number));
            long end = System.currentTimeMillis();
            return String.format("* %s, %dms *\n\n%s",result[1], end - start, result[0]);
        }

        @Override
        protected void onPostExecute(String result)
        {
            switch (result) {
                case "IV": // invalid
                    displayWarning();
                    break;
                default:
                    ((TextView) rootView.findViewById(R.id.textView)).setText(result);
            }
            progress.dismiss();
        }
    }
}
