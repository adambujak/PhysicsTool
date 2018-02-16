package com.limbwal.adam.physicstool;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class distance extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner from, to; // from = original units, to = desired units
    Button go;
    ImageView switchButton;
    TextView output;
    EditText input;
    int intFrom = -1;
    int intTo = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        to = (Spinner)findViewById(R.id.to);
        from = (Spinner) findViewById(R.id.from);
        go = (Button) findViewById(R.id.convertButton);
        output = (TextView) findViewById(R.id.result);
        switchButton = (ImageView) findViewById(R.id.switchButton);
        input = (EditText) findViewById(R.id.input);
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int buf = to.getSelectedItemPosition();
                to.setSelection(from.getSelectedItemPosition());
                from.setSelection(buf);
            }
        });

        final String[] terms = {"From", "metres", "kilometres", "centimetres", "millimetres",
                            "miles", "yards", "feet", "inches"};
        final String[] terms1= {"To", "metres", "kilometres", "centimetres", "millimetres",
                "miles", "yards", "feet", "inches"};
        final double[] conversion = {0, 1, 1000, 0.01, 0.001, 1609.34, 0.9144, 0.3048, 0.0254};
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                try{
                    double value = Double.parseDouble(text);
                    value*=(conversion[intFrom]);
                    value *= (1/conversion[intTo]);
                    BigDecimal a = new BigDecimal(value);
                    a = a.divide(new BigDecimal(1), 8, RoundingMode.HALF_UP);
                    String b = ""+a;
                    b = equals.removeTrailingZeros(b);
                    equals.answer = a;
                    output.setText(b+" "+terms[intTo]);

                }
                catch(NumberFormatException | NullPointerException e){
                    output.setText("Please enter valid values!");
                }
                catch (ArrayIndexOutOfBoundsException e){
                    output.setText("Please select valid values!");
                }

            }
        });
        dataConversion(terms, from);
        dataConversion(terms1, to);
    }
    public void dataConversion(String[] array, Spinner spinner) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array) {
            @Override
            public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {

                View v = super.getDropDownView(position, null, parent);
                parent.setVerticalScrollBarEnabled(false);
                return v;
            }
        };
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
    }
    public void onItemSelected(AdapterView<?> spinNum, View v, int position, long id) {
        switch (spinNum.getId()) {
            case R.id.from:
                switch (position) {
                    case 0:
                        intFrom = -1;
                        break;
                    default:
                        intFrom = position;

                }
                break;
            case R.id.to:
                switch (position) {
                    case 0:
                        intTo = -1;
                        break;
                    default:
                        intTo = position;

                }
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // doNothing
    }
}
