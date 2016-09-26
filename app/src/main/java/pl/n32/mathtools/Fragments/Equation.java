package pl.n32.mathtools.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import pl.n32.mathtools.Classes.ModularEquation;
import pl.n32.mathtools.R;
import pl.n32.mathtools.Validation;

public class Equation extends Fragment
{
    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_modular_equation, container, false);
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

    private void process()
    {
        String number1 = ((EditText) rootView.findViewById(R.id.number1)).getText().toString();
        String number2 = ((EditText) rootView.findViewById(R.id.number2)).getText().toString();
        String number3 = ((EditText) rootView.findViewById(R.id.number3)).getText().toString();

        if (Validation.isLong(number1) && Validation.isLong(number2) && Validation.isLong(number3)) {
            ModularEquation me = new ModularEquation(Long.parseLong(number1), Long.parseLong(number2), Long.parseLong(number3));
            String[] response = me.solveEquation();
            
            String result;
            if (response[0].equals("1"))
                result = response[1];
            else
                result = response[1] + getString(R.string.no_solution);

            ((TextView) rootView.findViewById(R.id.textView)).setText(result);
        }
        else {
            displayWarning();
        }
    }

    private void displayWarning()
    {
        Toast.makeText(getActivity(), R.string.cannot_process_that_value, Toast.LENGTH_SHORT).show();
    }
}
