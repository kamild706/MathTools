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

import java.util.ArrayList;

public class GreatestCommonDivisor extends Fragment
{
    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_gcd, container, false);
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
        Toast.makeText(getActivity(), R.string.two_positive_numbers, Toast.LENGTH_SHORT).show();
    }

    private class Calculate extends AsyncTask<Boolean, Void, String>
    {
        private ProgressDialog progress;
        private String[] numbers = new String[4];
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

            numbers[0] = ((EditText) rootView.findViewById(R.id.number1)).getText().toString();
            numbers[1] = ((EditText) rootView.findViewById(R.id.number2)).getText().toString();
            numbers[2] = ((EditText) rootView.findViewById(R.id.number3)).getText().toString();
            numbers[3] = ((EditText) rootView.findViewById(R.id.number4)).getText().toString();
        }

        @Override
        protected String doInBackground(Boolean... params)
        {
            ArrayList<Long> nums = new ArrayList<>();
            for (String s : numbers) {
                if (isNumberPositive(s))
                    nums.add(Long.parseLong(s));
            }

            if (nums.size() >= 2) {
                long a = nums.get(0);
                long b;
                for (int i = 1; i < nums.size(); i++) {
                    b = Numbers.getGreatestCommonDivisor(a, nums.get(i));
                    a = b;
                }

                StringBuilder sb = new StringBuilder("GCD: ").append(a).append("\n");
                for (Long l : nums)
                    sb.append(l).append(" / ").append(a).append(" = ").append(l / a).append("\n");

                sb.setLength(sb.length() - 1);
                long end = System.currentTimeMillis();

                return String.format("* %dms *\n\n%s", end - start, sb.toString());
            }
            else {
                return "IV";
            }
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

    private boolean isNumberPositive(String s)
    {
        if (Validation.isLong(s))
            if (Long.parseLong(s) > 0)
                return true;
        return false;
    }
}
