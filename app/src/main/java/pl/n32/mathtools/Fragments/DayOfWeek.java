package pl.n32.mathtools.Fragments;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import pl.n32.mathtools.Classes.BenjaminAlgorithm;
import pl.n32.mathtools.R;
import pl.n32.mathtools.Validation;

import java.util.Map;

public class DayOfWeek extends Fragment
{
    private View rootView;
    private Switch toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_dayofweek, container, false);

        toggle = (Switch) rootView.findViewById(R.id.hint_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                process();
            }
        });

        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                process();
            }
        });

        return rootView;
    }

    private void displayWarning()
    {
        Toast.makeText(getActivity(), R.string.improper_date, Toast.LENGTH_SHORT).show();
    }

    private void process()
    {
        String day = ((EditText) rootView.findViewById(R.id.day)).getText().toString();
        String month = ((EditText) rootView.findViewById(R.id.month)).getText().toString();
        String year = ((EditText) rootView.findViewById(R.id.year)).getText().toString();

        if (!(Validation.isInteger(day) && Validation.isInteger(month) && Validation.isInteger(year))) {
            displayWarning();
            return;
        }

        BenjaminAlgorithm ba = new BenjaminAlgorithm(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
        if (ba.isDateValid()) {
            Map<String, Integer> calculations = ba.getCalculations();
            String result = getFormattedResult(calculations);

            TextView tv = (TextView) rootView.findViewById(R.id.textView);
            tv.setText(result);
            tv.setGravity((toggle.isChecked() ? Gravity.START : Gravity.CENTER));
        }
        else {
            displayWarning();
        }
    }

    @SuppressLint("DefaultLocale")
    private String getFormattedResult(Map<String, Integer> calculations)
    {
        String[] days = {
                getString(R.string.sunday),
                getString(R.string.monday),
                getString(R.string.tuesday),
                getString(R.string.wednesday),
                getString(R.string.thursday),
                getString(R.string.friday),
                getString(R.string.saturday)
        };

        String[] months = {
                getString(R.string.january),
                getString(R.string.february),
                getString(R.string.march),
                getString(R.string.april),
                getString(R.string.may),
                getString(R.string.june),
                getString(R.string.july),
                getString(R.string.august),
                getString(R.string.september),
                getString(R.string.october),
                getString(R.string.november),
                getString(R.string.december)
        };

        String[] monthsGenitive = {
                getString(R.string.january_genitive),
                getString(R.string.february_genitive),
                getString(R.string.march_genitive),
                getString(R.string.april_genitive),
                getString(R.string.may_genitive),
                getString(R.string.june_genitive),
                getString(R.string.july__genitive),
                getString(R.string.august_genitive),
                getString(R.string.september_genitive),
                getString(R.string.october_genitive),
                getString(R.string.november_genitive),
                getString(R.string.december_genitive)
        };


        if (toggle.isChecked()) {
            return String.format(
                    "1. %d \u2261 %d (mod 7)\n" +
                            "2. %s offset: %d\n" +
                            "3. %d \u2261 %d (mod 7)\n" +
                            "4. \u230a%d/4\u230b = %d\n" +
                            "5. Century's offset: %d\n" +
                            "6. %d+%d+%d+%d+%d=%d\n" +
                            "7. %d \u2261 %d (mod 7)\n" +
                            "%s",
                    calculations.get("day"), calculations.get("day_value"),
                    months[calculations.get("month") - 1], calculations.get("monthOffset_value"),
                    calculations.get("year") % 100, calculations.get("year1_value"),
                    calculations.get("year") % 100, calculations.get("year2_value"),
                    calculations.get("centuryOffset_value"),
                    calculations.get("day_value"), calculations.get("monthOffset_value"), calculations.get("year1_value"), calculations.get("year2_value"), calculations.get("centuryOffset_value"), calculations.get("sum"),
                    calculations.get("sum"), calculations.get("sum") % 7,
                    days[calculations.get("sum") % 7]
            );
        }
        else {
            return String.format("%s %s %s %s %s",
                    calculations.get("day"), monthsGenitive[calculations.get("month") - 1],
                    calculations.get("year"), getString(R.string.is), days[calculations.get("sum") % 7]
            );
        }
    }
}
