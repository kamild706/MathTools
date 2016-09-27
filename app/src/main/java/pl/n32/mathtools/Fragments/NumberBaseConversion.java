package pl.n32.mathtools.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import pl.n32.mathtools.Classes.NumberBaseConverter;
import pl.n32.mathtools.R;
import pl.n32.mathtools.Validation;


public class NumberBaseConversion extends Fragment
{
    public View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_number_base_convertion, container, false);
        rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                process(
                        ((EditText) rootView.findViewById(R.id.number)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.current_base)).getText().toString(),
                        ((EditText) rootView.findViewById(R.id.new_base)).getText().toString()
                );
            }
        });

        return rootView;
    }

    private void displayWarning()
    {
        Toast.makeText(getActivity(), R.string.improper_values, Toast.LENGTH_SHORT).show();
    }

    private void process(String number, String base, String newBase)
    {
        if (Validation.isInteger(base) && Validation.isInteger(newBase)) {
            String result;
            try {
                NumberBaseConverter nbc = new NumberBaseConverter(number, Integer.parseInt(base));
                result = nbc.convertToBase(Integer.parseInt(newBase));
            }
            catch (Exception e) {
                displayWarning();
                return;
            }

            ((TextView) rootView.findViewById(R.id.textView)).setText(result);
        }
        else {
            displayWarning();
        }
    }
}
