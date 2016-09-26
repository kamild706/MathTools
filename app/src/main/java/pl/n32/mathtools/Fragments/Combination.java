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

public class Combination extends Fragment
{
    private View rootView;
    private Switch toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_combination, container, false);

        toggle = (Switch) rootView.findViewById(R.id.combination_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if (isChecked)
                    toggle.setText(R.string.with_repetitions);
                else
                    toggle.setText(R.string.without_repetitions);

                new Calculate().execute();
            }
        });

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
        private String set;
        private String subset;
        private boolean flag;
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

            set = ((EditText) rootView.findViewById(R.id.set)).getText().toString();
            subset = ((EditText) rootView.findViewById(R.id.subset)).getText().toString();
            flag = toggle.isChecked();
        }

        @Override
        protected String doInBackground(Boolean... params)
        {
            if (!(Validation.isLong(set) && Validation.isLong(subset))) {
                return "IV"; // invalid
            }
            String result;
            if (flag)
                result = Combinatorics.multicombination(Long.parseLong(set), Long.parseLong(subset));
            else
                result = Combinatorics.combination(Long.parseLong(set), Long.parseLong(subset));

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