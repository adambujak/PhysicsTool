package com.limbwal.adam.physicstool;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class equationSet extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] unitItems = {"Choose Unit", "Kinematics", "Forces", "Waves"};
    static String[][] equations = {
            {"1", "1", "Displacement-Velocity-Time", "d=vt", "dvt", "d", "v", "t", "distance"},
            {"1", "1", "Displacement-Velocity-Time", "v=d/t", "vdt", "v", "d", "t", "speed"},
            {"1", "1", "Displacement-Velocity-Time", "t=d/v", "tdv", "t", "d", "v", "time"},
            {"2", "1", "Acceleration-Velocity-Time", "a=(v₂-v₁)/t", "avt", "a", "v₂", "v₁", "t", "acceleration"},
            {"2", "1", "Acceleration-Velocity-Time", "v₂=atv₁", "v2at", "v₂", "v₁", "a", "t", "speed"},
            {"2", "1", "Acceleration-Velocity-Time", "v₁=v₂-at", "v1at", "v₁", "v₂", "a", "t", "speed"},
            {"2", "1", "Acceleration-Velocity-Time", "t=(v₂-v₁)/a", "tva", "t", "v₂", "v₁", "a", "time"},
            {"3", "1", "Acceleration-Velocity-Displacement", "v₂2=v₁2+2ad", "v2ad", "v₂", "v₁", "a", "d", "speed"},
            {"3", "1", "Acceleration-Velocity-Displacement", "v₁2=v₂2-2ad", "v1ad", "v₁", "v₂", "a", "d", "speed"},
            {"3", "1", "Acceleration-Velocity-Displacement", "a=(v₂2-v₁2)/2d", "avd", "a", "v₂", "v₁", "d", "acceleration"},
            {"3", "1", "Acceleration-Velocity-Displacement", "d=(v₂2-v₁2)/2a", "dva", "d", "v₂", "v₁", "a", "distance"},
            {"4", "1", "Displacement-Velocity1&2-Time", "d=(v1+v2)t/2", "dvvt", "d", "v1", "v2", "t", "distance"},
            {"4", "1", "Displacement-Velocity1&2-Time", "v1=2d/t-v2", "v1dvt", "v1", "d", "t", "v2", "speed"},
            {"4", "1", "Displacement-Velocity1&2-Time", "v2=2d/t-v1", "v2dvt", "v2", "d", "t", "v1", "speed"},
            {"4", "1", "Displacement-Velocity1&2-Time", "t=2d/(v1+v2)", "tdvv", "t", "d", "v1", "v2", "time"},
            {"5", "1", "Displacement-Velocity-Time-Acceleration", "d=vt+0.5at**2", "dvat", "d", "v", "a" ,"t", "distance"},
            {"5", "1", "Displacement-Velocity-Time-Acceleration", "v=d-0.5at**2", "v1dat", "v", "d", "t", "a", "speed"},
            {"5", "1", "Displacement-Velocity-Time-Acceleration", "a=2(d-vt)/(t*t)", "advt", "a", "d", "v", "t", "acceleration"},
            {"5", "1", "Displacement-Velocity-Time-Acceleration", "t=v1+(v1*v1+2ad)**0.5", "tdva", "t", "v", "a", "d", "time"},
            {"5", "1", "Displacement-Velocity-Time-Acceleration", "t=v1-(v1*v1+2ad)**0.5", "tdva", "t", "v", "a", "d", "time"},
            {"4", "2", "Force-Mass-Acceleration", "F=ma", "fma", "F", "m", "a", "force"},
            {"4", "2", "Force-Mass-Acceleration", "m=f/a", "mfa", "m", "F", "a", "mass"},
            {"4", "2", "Force-Mass-Acceleration", "a=f/m", "afm", "a", "F", "m", "acceleration"}
    };
    int numEquations = equations.length;

    String[] unit1Items = splitArrayIntoUnitN(equations, 1);
    String[] unit2Items = splitArrayIntoUnitN(equations, 2);
    String[] unit3Items = splitArrayIntoUnitN(equations, 3);
    String[][] unitItemsArray = {unit1Items, unit2Items};
    static int equationNum = 0;
    Spinner unitSpinner;
    Spinner equationSpinner;
    Spinner variantSpinner;
    Button ok, cancel;

    static int equation = -1;
    ImageView equationGlimpse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_set);
        unitSpinner = (Spinner) findViewById(R.id.unitSpinner);
        equationSpinner = (Spinner) findViewById(R.id.equationSpinner);
        equationGlimpse = (ImageView) findViewById(R.id.equationGlimpse);
        variantSpinner = (Spinner) findViewById(R.id.variantSpinner);
        ok = (Button) findViewById(R.id.okButton);
        assert ok != null;
        ok.setOnClickListener(clickListener);
        cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                equation = -1;
                goToWorkSpace();
            }
        });
        dataConversion(unitItems, unitSpinner);
        unitSpinner.setSelection(getIndex(unitSpinner, unitItems[workspace.unit]));
       // equationSpinner.setSelection(getIndex(equationSpinner, unitItemsArray[workspace.unit][workspace.equationPosition]));
        variantSpinner.setSelection(workspace.variantNumber);
        switch (workspace.unit){
            case 1:
                dataConversion(unit1Items, equationSpinner);
                break;
            case 2:
                dataConversion(unit2Items, equationSpinner);
                break;
            case 3:
                dataConversion(unit3Items, equationSpinner);
                break;
        }
        variantSpinner.setSelection(workspace.variantNumber);

        equationSpinner.setSelection(workspace.equationPosition);

    }

    public void onItemSelected(AdapterView<?> spinNum, View v, int position, long id) {

        switch (spinNum.getId()) {

            case R.id.unitSpinner:
                workspace.unit = position;
                switch (position) {
                    case 1:
                        dataConversion(unit1Items, equationSpinner);
                        break;
                    case 2:
                        dataConversion(unit2Items, equationSpinner);
                        break;
                    case 3:
                        dataConversion(unit3Items, equationSpinner);
                        break;
                }
                workspace.equationGroup = position;
                break;
            case R.id.equationSpinner:
                String term = "";

                if (workspace.unit == 1) {
                    term = unit1Items[position];
                }
                if (workspace.unit == 2) {
                term = unit2Items[position];
                }
                if (workspace.unit == 3) {
                    term = unit3Items[position];
                }

                String[] variantItems = findVariables(equations, term);
                dataConversion(variantItems, variantSpinner);
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                }
                break;
            case R.id.variantSpinner:
                switch (position) {

                    case 0:

                        break;
                    case 1:
                        equationNum = equation;
                        break;
                    case 2:

                        equationNum = equation + 1;
                        break;
                    case 3:

                        equationNum = equation + 2;
                        break;
                    case 4:
                        equationNum = equation + 3;

                        break;
                    case 5:
                        equationNum = equation + 4;
                        break;
                }
                workspace.variantNumber = position;

                String imageName = equations[equationNum][4];

                int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                System.out.println(imageName);

                equationGlimpse.setImageResource(imageId);
                break;
        }
        int currentApiVersion;
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // doNothing
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

    public int[] findUnitNRange(String[][] array, int n) {
        int start = 0;
        int end = 0;
        try {
            for (int cnt = 0; cnt < numEquations; cnt++) {
                if (Integer.parseInt(array[cnt][1]) == n) {
                    start = cnt;
                    break;
                }
            }
            for (int cnt = start; cnt < numEquations; cnt++) {
                if (Integer.parseInt(array[cnt][1]) == n + 1) {
                    end = cnt;
                    break;
                }
                if (cnt == numEquations - 1) {
                    end = cnt;
                    break;
                }
            }
        } catch (NumberFormatException ignored) {
        }
        return new int[]{start, end};
    }

    public String[] splitArrayIntoUnitN(String[][] array, int n) {
        int[] range = findUnitNRange(array, n);
        int start = range[0];
        int end = range[1];
        System.out.println("start " + start);
        System.out.println("end " + end);
        int length = end - start;
        if (length == 0) {
            length = 1;
        }
        length++;
        String[] newArray = new String[length];
        newArray[0] = "Choose an Equation";
        int a = 1;
        newArray[a] = equations[start][2];
        a++;
        for (int cnt = start + 1; cnt < end; cnt++) {


            if (!(newArray[a - 1].equals(equations[cnt][2]))) {
                newArray[a] = equations[cnt][2];
                a++; // to not have multiple same string values
            }
        }

        newArray = cleanArray(newArray);
        return newArray;
    }

    public static String[] cleanArray(String[] array) {
        int newLength = 0;
        for (String anArray : array) {
            if (!(anArray == null || anArray.equals(""))) {
                newLength++;
            }
        }
        String[] newArray = new String[newLength];
        for (int cnt = 0; cnt < newLength; cnt++) {
            if (!(array[cnt] == null || array[cnt].equals(""))) {
                newArray[cnt] = array[cnt];
            }
        }
        return newArray;
    }

    public String[] findVariables(String[][] array, String text) {
        System.out.println(text);
        int length = numEquations;
        int length1 = 9;
        int newLength = 7;
        String[] newArray = new String[newLength];
        newArray[0] = "Rearrange for:";
        int location = 0;
        for (int cnt = 0; cnt < length; cnt++) {
            if (text.equals(array[cnt][2])) {
                location = cnt;
                break;
            }
        }
        equation = location;
        try {
            int a = 1;
            for (int cnt = 5; cnt <= length1; cnt++) {
                newArray[a] = array[location][cnt];
                a++;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        newArray = workspace.cleanArray(newArray);
        return newArray;
    }

    public static int getEquation() {
        return equation;
    }

    public void goToWorkSpace() {
        Intent b = new Intent(this, workspace.class);
        startActivity(b);
    }
    public View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            equation = equationNum;
            workspace.equationChoice=true;
            goToWorkSpace();
        }
    };

    private int getIndex(Spinner spinner, String myString){
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}
