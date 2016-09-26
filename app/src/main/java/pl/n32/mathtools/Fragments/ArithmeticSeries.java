package pl.n32.mathtools.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import pl.n32.mathtools.Classes.ArithmeticSequence;
import pl.n32.mathtools.R;
import pl.n32.mathtools.Validation;

public class ArithmeticSeries extends Fragment
{
    public View rootView;
    public Switch toggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_arithmetic_sequence, container, false);

        toggle = (Switch) rootView.findViewById(R.id.sequence_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if (isChecked)
                    toggle.setText(R.string.calc_product);
                else
                    toggle.setText(R.string.calc_sum);
            }
        });

        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                process(
                        ((EditText) rootView.findViewById(R.id.a)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.r)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.n)).getText().toString()
                );
            }
        });
        return rootView;
    }

    private void displayWarning()
    {
        Toast.makeText(getActivity(), R.string.improper_values, Toast.LENGTH_SHORT).show();
    }

    private void process(String a, String r, String n)
    {
        Long start = System.currentTimeMillis();

        if (Validation.isLong(a) && Validation.isLong(r) && Validation.isLong(n)) {
            ArithmeticSequence as = new ArithmeticSequence(Long.parseLong(a), Long.parseLong(r), Long.parseLong(n));

            String sum;
            if (toggle.isChecked())
                sum = as.getProduct();
            else
                sum = as.getSum();

            Long end = System.currentTimeMillis();
            ((TextView) rootView.findViewById(R.id.textView)).setText(String.format("* %dms *\n\n%s", end - start, sum));
        }
        else {
            displayWarning();
        }
    }
}
