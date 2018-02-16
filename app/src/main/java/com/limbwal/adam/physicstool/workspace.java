package com.limbwal.adam.physicstool;


import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.limbwal.adam.physicstool.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
/*
this contains the declarations for all the buttons and fields in the workspace.
 */

public class workspace extends AppCompatActivity    {

    static String[][] equations = equationSet.equations;
    double a=1.1,b=2.2,c=2.3;//random values only to initialize - this is for equation solver
    int equation =0;
    EditText twoVarA;
    EditText twoVarB;
    EditText threeVarA;
    EditText threeVarB;
    EditText threeVarC;
    EditText fourVarA;
    EditText fourVarB;
    EditText fourVarC;
    EditText fourVarD;
    ImageView equationView;
    ImageView backArrow;
    TextView calculation;
    Button calculate;
    Button numZero;
    Button numOne;
    Button numTwo;
    Button numThree;
    Button numFour;
    Button numFive;
    Button numSix;
    Button numSeven;
    Button numEight;
    Button numNine;
    Button equalButton;
    Button toggleSecondary;
    Button clear;
    Button pi;
    Button naturalLog;
    Button inverse;
    Button factorial;
    Button bracket_Open;
    Button bracket_Close;
    Button plus;
    Button mult;
    Button squared;
    Button toThePowerOf;
    Button xRoot;
    Button divis;
    Button log;
    Button decimal;
    Button subtraction;
    Button exp;
    Button AngleType;
    Button ans;
    static int unit;
    Button squareRoot;
    Button sin;
    Button tan;
    Button cos;
    Button inverseCos;
    Button inverseSin;
    Button inverseTan;
    Button inverseLog;
    Button fraction;
    Button NumberE;
    Button back;
    Button inverseLN;
    Button[] secondary;
    Button[] primary;
    Button[] primaryOnesToHide;
    Button[] constantPrimary;
    Button[] inverseButtons;
    Button[] secondaryInvButtons;
    TextView inverseLNIndicator;
    EditText [] editTextArray;
    EditText screen;
    int toggleCount = 0;
    int numVar = 0;
    int inverseCount = 0;
    public static int equationGroup = 0;
    public static int equationPosition = 0;
    public static int variantNumber = 0;
    public static int angleTypeCounter = 0;
    public static String editTextSelection = "screen";
    static boolean equationChoice = false;
    public String[][] units = {{"m", "f"},{"m/s", "f/s"},{"m/s²", "f/s²"},{"N", "lbf"}, {"s", "s"}, {"kg", "lb"}};
    boolean degree = true;
    private int currentApiVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        equationView = (ImageView) findViewById(R.id.equationView);
        assert equationView != null;
        equationView.setOnClickListener(clickListener);
        equationView.setImageResource(R.drawable.button);
        twoVarA = (EditText) findViewById(R.id.twoVarA);
        twoVarB = (EditText) findViewById(R.id.twoVarB);
        threeVarA = (EditText) findViewById(R.id.threeVarA);
        threeVarB = (EditText) findViewById(R.id.threeVarB);
        threeVarC = (EditText) findViewById(R.id.threeVarC);
        fourVarA = (EditText) findViewById(R.id.FourVarA);
        fourVarB = (EditText) findViewById(R.id.FourVarB);
        fourVarC = (EditText) findViewById(R.id.FourVarC);
        fourVarD = (EditText) findViewById(R.id.FourVarD);
        calculation = (TextView) findViewById(R.id.calculation);
        calculate = (Button) findViewById(R.id.calculate);
        backArrow = (ImageView) findViewById(R.id.backArrow);

        numZero = (Button) findViewById(R.id.num0);
        numOne = (Button) findViewById(R.id.num1);
        numTwo = (Button) findViewById(R.id.num2);
        numThree = (Button) findViewById(R.id.num3);
        numFour = (Button) findViewById(R.id.num4);
        numFive = (Button) findViewById(R.id.num5);
        numSix = (Button) findViewById(R.id.num6);
        numSeven = (Button) findViewById(R.id.num7);
        numEight = (Button) findViewById(R.id.num8);
        numNine = (Button) findViewById(R.id.num9);

        equalButton = (Button) findViewById(R.id.equalButton);
        clear = (Button) findViewById(R.id.clear);
        screen = (EditText) findViewById(R.id.screen);
        plus = (Button) findViewById(R.id.plus);
        mult = (Button) findViewById(R.id.multi);
        decimal = (Button) findViewById(R.id.decimal);
        divis = (Button) findViewById(R.id.division);
        subtraction = (Button) findViewById(R.id.subtract);
        ans = (Button) findViewById(R.id.answer);
        exp = (Button) findViewById(R.id.exponent);
        pi = (Button) findViewById(R.id.pi);
        decimal = (Button) findViewById(R.id.decimal);
        exp = (Button) findViewById(R.id.exponent);
        ans = (Button) findViewById(R.id.answer);
        back = (Button) findViewById(R.id.back);
        toggleSecondary = (Button) findViewById(R.id.toggleSecondary);

        inverse= (Button) findViewById(R.id.Inverse);
        factorial = (Button) findViewById(R.id.factorial);
        naturalLog = (Button) findViewById(R.id.naturalLog);
        squared = (Button) findViewById(R.id.square);
        toThePowerOf = (Button) findViewById(R.id.toThePowerOf);
        toThePowerOf.setText(Html.fromHtml("x<sup><small>y</small></sup>"));
        xRoot = (Button) findViewById(R.id.xRoot);
        xRoot.setText(Html.fromHtml("<sup><small>x</small></sup>√"));
        fraction = (Button) findViewById(R.id.fraction);
        log = (Button) findViewById(R.id.log);
        inverseCos = (Button) findViewById(R.id.inverseCos);
        squareRoot = (Button) findViewById(R.id.sqrt);
        bracket_Open = (Button) findViewById(R.id.bracket_open);
        bracket_Close = (Button) findViewById(R.id.bracket_closed);
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);

        NumberE = (Button) findViewById(R.id.numberE);
        inverseLNIndicator = (TextView) findViewById(R.id.inverseLNIndicator);
        AngleType = (Button) findViewById(R.id.AngleType);
        inverseLN = (Button) findViewById(R.id.inverseNaturalLog);
        inverseSin = (Button) findViewById(R.id.inverseSin);
        inverseTan = (Button) findViewById(R.id.inverseTan);
        inverseLog = (Button) findViewById(R.id.inverseLog);
        inverseSin.setText(Html.fromHtml("sin<sup><small>-1</small></sup>"));
        inverseCos.setText(Html.fromHtml("cos<sup><small>-1</small></sup>"));
        inverseTan.setText(Html.fromHtml("tan<sup><small>-1</small></sup>"));
        inverseLog.setText(Html.fromHtml("10<sup><small>x</small></sup>"));
        inverseLN.setText(Html.fromHtml("e<sup><small>x</small></sup>"));

        primary = new Button[]{
                numOne, numTwo, numThree, numFour, numFive, numSix, numSeven,
                numEight, numNine, numZero, decimal, exp, ans, clear, back,
                plus, subtraction, mult, divis, bracket_Close, bracket_Open
        };
        primaryOnesToHide = new Button[]{
                numTwo, numThree, numFive, numSix,  numEight, numNine, numZero, bracket_Open, bracket_Close, exp, ans
        };
        constantPrimary = new Button[]{
                numOne, numFour, numSeven, clear, decimal
                // these are buttons that stay visible when the secondary ones appear
        };
        secondary = new Button[]{
                log, sin, cos, tan, fraction, NumberE,
                squareRoot, toThePowerOf, xRoot, squared,  naturalLog,
                inverse, factorial, pi
        };
        declareOnClick(primary);
        declareOnClick(secondary);
        inverseButtons = new Button[]{
                inverseSin, inverseCos, inverseTan, inverseLN, inverseLog
        };
        AngleType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angleTypeCounter++;
                if (angleTypeCounter % 2 == 0){
                    AngleType.setText("Deg");
                }
                else{
                    AngleType.setText("Rad");
                }
            }
        });


        editTextArray = new EditText[]{
                twoVarA, twoVarB, screen, threeVarA, threeVarB, threeVarC
        };
        setSecondaryInVisible();

        setInverseInVisible();
        declareOnClick(inverseButtons);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.setText("");
                if (toggleCount%2!=0){
                    setPrimaryVisible();
                    setSecondaryInVisible();
                    setInverseInVisible();
                    inverse.setBackgroundColor(getColor(R.color.colorPrimary));
                    toggleCount++;

                }
            }
        });
        if (equationChoice){
            calculate.setVisibility(View.VISIBLE);
        }
        secondaryInvButtons = new Button[]{
                sin, cos, tan, log, naturalLog
        };

        inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inverseCount++;
                if (inverseCount%2==0){
                    setSecondaryInverseVisible();
                    setInverseInVisible();
                    inverse.setBackgroundColor(getColor(R.color.colorPrimary));
                }
                else{
                    setSecondaryInverseInVisible();
                    setInverseVisible();
                    inverse.setBackgroundColor(getColor(R.color.colorPrimaryDark));
                }
            }
        });

        //opens and closes secondary buttons
        toggleSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleCount++;
                inverseCount = 0;
                inverse.setBackgroundColor(getColor(R.color.colorPrimary));
                if (toggleCount%2==0){
                    setSecondaryInVisible();
                    setPrimaryVisible();
                    setInverseInVisible();
                }
                else {
                    setSecondaryVisible();
                    setPrimaryInVisible();
                }
            }
        });


        for (EditText anEditTextArray : editTextArray) {
            anEditTextArray.setShowSoftInputOnFocus(false);//hides keyboard when user is on input
        }


        backArrow.setImageResource(R.drawable.backarrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
        equation = equationSet.getEquation();
        String[] hintValues = {""};

        if (equation!=-1){ //equation has been set because equation is -1 until set
            String imageName = equations[equation][4]; //gets equation image name
            int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            equationView.setImageResource(imageId);
            hintValues = findVariables(equations, equation);

        }

        final int length = hintValues.length; //number of variables there are
        System.out.println("length "+length);
        if (length == 2){
            twoVariables();
            twoVarA.setHint(hintValues[0]);
            twoVarB.setHint(hintValues[1]);
            numVar =2;
        }
        else if (length == 3){
            threeVariables();
            threeVarA.setHint(hintValues[0]);
            threeVarB.setHint(hintValues[1]);
            threeVarC.setHint(hintValues[2]);
            numVar = 3;
        }

        else if (length == 4){
            fourVariables();
            fourVarA.setHint(hintValues[0]);
            fourVarB.setHint(hintValues[1]);
            fourVarC.setHint(hintValues[2]);
            fourVarD.setHint(hintValues[3]);
            numVar = 4;

        }

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = screen.getText().toString();
                    if (angleTypeCounter % 2 == 0) {
                        degree = true;
                    } else {
                        degree = false;
                    }

                    text = equals.calculate(text);
                    BigDecimal answer = new BigDecimal(text);
                    answer = answer.divide(new BigDecimal(1), 10, RoundingMode.HALF_UP);

                    screen.setText(equals.removeTrailingZeros(answer+""));
                    screen.setSelection(screen.getText().length());
                }
                catch (ArithmeticException | NumberFormatException e){
                    screen.setText("Error!");
                }

            }
        });


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    switch (numVar){
                        case 2:

                            a=Double.parseDouble(equals.calculate(twoVarA.getText().toString()));
                            b=Double.parseDouble(equals.calculate(twoVarB.getText().toString()));

                            break;
                        case 3:


                            a=Double.parseDouble(equals.calculate(threeVarA.getText().toString()));
                            b=Double.parseDouble(equals.calculate(threeVarB.getText().toString()));
                            c=Double.parseDouble(equals.calculate(threeVarC.getText().toString()));

                            break;
                        /*
                        case 4:
                            a=Double.parseDouble(fourVarA.getText().toString());
                            b=Double.parseDouble(fourVarB.getText().toString());
                            c=Double.parseDouble(fourVarC.getText().toString());
                            d=Double.parseDouble(fourVarD.getText().toString());
                            break;
                            */
                    }

                    double total = doubleCalculate(a,b,c, equation);
                    if (equation == 18&&total<=0){//quadratic
                        total = doubleCalculate(a,b,c, equation+1);
                    }
                    BigDecimal sum = new BigDecimal(total);
                    sum = sum.divide(new BigDecimal(1), MainActivity.numDig, RoundingMode.HALF_UP);
                    equals.answer = sum;
                    String dataType = equationSet.equations[equation][equationSet.equations[equation].length-1];

                    String end = "";
                    switch (dataType){
                        case "force":
                            end = units[3][MainActivity.unitNumber];
                            break;
                        case "mass":
                            end = units[5][MainActivity.unitNumber];
                            break;

                        case "acceleration":
                            end = units[2][MainActivity.unitNumber];
                            break;
                        case "speed":
                            end = units[1][MainActivity.unitNumber];
                            break;
                        case "distance":
                            end = units[0][MainActivity.unitNumber];
                            break;
                        case "time":
                            end = units[4][MainActivity.unitNumber];
                            break;
                    }
                    if (sum == new BigDecimal(0)){
                        sum = new BigDecimal(0);//only to remove trailign zeroes
                    }
                    String output = sum+end;
                    calculation.setText(output);
                }

                catch (NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException e){
                    if(!(equationChoice))
                        calculation.setText(getResources().getString(R.string.choose_equation));
                    else {
                        calculation.setText(getResources().getString(R.string.enter_values));
                    }
                }
            }
        });




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

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public void goToEquationSet() {
        Intent a = new Intent(this, equationSet.class);
        startActivity(a);
    }

    public void goHome() {

        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
    }
    private View.OnClickListener clickListener = new View.OnClickListener() {

        public void onClick(View v) {
            goToEquationSet();

        }
    };
    public void twoVariables(){
        setTwoVisible();
        setThreeInVisible();
        setFourInVisible();
    }
    public void threeVariables(){
        setTwoInVisible();
        setThreeVisible();
        setFourInVisible();
    }
    public void fourVariables(){
        setTwoInVisible();
        setThreeInVisible();
        setFourVisible();
    }
    public void setTwoVisible(){
        twoVarA.setVisibility(View.VISIBLE);
        twoVarB.setVisibility(View.VISIBLE);
    }
    public void setTwoInVisible(){
        twoVarA.setVisibility(View.INVISIBLE);
        twoVarB.setVisibility(View.INVISIBLE);
    }
    public void setThreeVisible(){
        threeVarA.setVisibility(View.VISIBLE);
        threeVarB.setVisibility(View.VISIBLE);
        threeVarC.setVisibility(View.VISIBLE);
    }
    public void setThreeInVisible(){
        threeVarA.setVisibility(View.INVISIBLE);
        threeVarB.setVisibility(View.INVISIBLE);
        threeVarC.setVisibility(View.INVISIBLE);
    }
    public void setFourVisible(){
        fourVarA.setVisibility(View.VISIBLE);
        fourVarB.setVisibility(View.VISIBLE);
        fourVarC.setVisibility(View.VISIBLE);
        fourVarD.setVisibility(View.VISIBLE);
    }
    public void setFourInVisible(){
        fourVarA.setVisibility(View.INVISIBLE);
        fourVarB.setVisibility(View.INVISIBLE);
        fourVarC.setVisibility(View.INVISIBLE);
        fourVarD.setVisibility(View.INVISIBLE);
    }
    public String[] findVariables(String[][] array, int n) {
        int length1 = 9;
        int newLength = 7;
        String[] newArray = new String[newLength];
        try {
            int a = 0;
            for (int cnt = 6; cnt <= length1; cnt++) {
                newArray[a] = array[n][cnt];
                a++;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {}
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
        String[] newArray = new String[newLength-1];
        for (int cnt = 0; cnt < newLength-1; cnt++) {
            if (!(array[cnt] == null || array[cnt].equals(""))) {
                newArray[cnt] = array[cnt];
            }
        }
        return newArray;
    }
    public View.OnClickListener everyButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EditText editTextBuffer = screen;
            editTextSelection = ""+getCurrentFocus();
            editTextSelection=stripId(editTextSelection);

            switch (editTextSelection){
                case "twoVarA":
                    editTextBuffer = twoVarA;
                    break;
                case "twoVarB":
                    editTextBuffer = twoVarB;
                    break;
                case "threeVarA":
                    editTextBuffer = threeVarA;
                    break;
                case "threeVarB":
                    editTextBuffer = threeVarB;
                    break;
                case "threeVarC":
                    editTextBuffer = threeVarC;
                    break;
                case "screen":
                    editTextBuffer = screen;
                    break;
            }
            int start = editTextBuffer.getSelectionStart();
            if ((v.getId()==R.id.num4||v.getId()==R.id.num7||
                    v.getId()==R.id.num1||v.getId()==R.id.back||
                    v.getId()==R.id.decimal)&&toggleCount%2!=0) {
                setPrimaryVisible();
                setSecondaryInVisible();
                setInverseInVisible();
                inverse.setBackgroundColor(getColor(R.color.colorPrimary));
                toggleCount++;
            }
            else {
                EveryButton.proceed(editTextBuffer, start, v);

            }
        }
    };
    public static double doubleCalculate(double a, double b, double c, int n){
        double[] formulas = {a*b,
                a/b,
                a/b,
                (a-b)/c,
                a+b*c,
                a-(b*c),
                (a-b)/c,
                Math.sqrt((Math.pow(a, 2))+(2*b*c)),
                Math.sqrt((Math.pow(a, 2))-(2*b*c)),
                (((Math.pow(a, 2))-(Math.pow(b,2)))/(2*c)),
                (((Math.pow(a, 2))-(Math.pow(b,2)))/(2*c)),
                ((a+b)/2)*c,
                2*a/b-c,
                2*a/b-c,
                2*a/(b+c),
                (a*b)+0.5*b*c*c,
                (a-0.5*c*b*b)/b,
                (2*(a-b*c))/(c*c),
                (a+Math.pow((a*a+2*b*c), 0.5))/b,
                (a-Math.pow((a*a+2*b*c), 0.5))/b,
                a*b,
                a/b,
                a/b};

        return formulas[n];
    }
    public static String stripId(String text){ //I use this to get only the useful part of the id
        int length = text.length();
        boolean a = false;
        String newString = "";
        for (int cnt = 0; cnt < length-1; cnt++){
            if (a){
                newString+=text.charAt(cnt);
            }
            if(text.charAt(cnt)=='/'){
                a = true;
            }
        }
        return newString;
    }
    public void setSecondaryVisible(){
        for (Button aSecondary : secondary) {
            aSecondary.setVisibility(View.VISIBLE);
        }
        AngleType.setVisibility(View.VISIBLE);
    }
    public void setPrimaryVisible(){
        for (Button aPrimary : primary) {
            aPrimary.setVisibility(View.VISIBLE);
        }
    }
    public void setPrimaryInVisible(){
        for (Button aPrimaryOnesToHide : primaryOnesToHide) {
            aPrimaryOnesToHide.setVisibility(View.INVISIBLE);
        }
    }
    public void setSecondaryInVisible(){
        for (Button aSecondary : secondary) {
            aSecondary.setVisibility(View.INVISIBLE);
        }
        AngleType.setVisibility(View.INVISIBLE);
    }
    public void setInverseInVisible(){
        for (Button aInverseButtons : inverseButtons) {
            aInverseButtons.setVisibility(View.INVISIBLE);
        }
    }
    public void setInverseVisible(){
        for (Button aInverseButtons : inverseButtons) {
            aInverseButtons.setVisibility(View.VISIBLE);
        }
    }
    public void setSecondaryInverseVisible(){
        for (Button aInverseButtons : secondaryInvButtons) {
            aInverseButtons.setVisibility(View.VISIBLE);
        }
    }
    public void setSecondaryInverseInVisible(){
        for (Button aInverseButtons : secondaryInvButtons) {
            aInverseButtons.setVisibility(View.INVISIBLE);
        }
    }
    public void declareOnClick(Button[] buttons){
        for (Button button : buttons) {
            button.setOnClickListener(everyButton);
        }
    }

}
