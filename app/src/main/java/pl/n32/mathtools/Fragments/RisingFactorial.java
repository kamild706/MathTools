package pl.n32.mathtools.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.*;
import pl.n32.mathtools.Classes.Combinatorics;
import pl.n32.mathtools.R;
import pl.n32.mathtools.Validation;

public class RisingFactorial extends Fragment
{
    private View rootView;
    private Switch toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_risingfactorial, container, false);
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
        Toast.makeText(getActivity(), R.string.improper_values, Toast.LENGTH_SHORT).show();
    }

    private class Calculate extends AsyncTask<Boolean, Void, String>
    {
        private ProgressDialog progress;
        private String base;
        private String exponent;
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

            base = ((EditText) rootView.findViewById(R.id.base)).getText().toString();
            exponent = ((EditText) rootView.findViewById(R.id.exponent)).getText().toString();
        }

        @Override
        protected String doInBackground(Boolean... params)
        {
            if (!(Validation.isLong(base) && Validation.isLong(exponent))) {
                return "IV"; // invalid
            }

            String result = Combinatorics.risingFactorial(Long.parseLong(base), Long.parseLong(exponent));

            long end = System.currentTimeMillis();
            return String.format("* %dms *\n\n%s", end - start, result);
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