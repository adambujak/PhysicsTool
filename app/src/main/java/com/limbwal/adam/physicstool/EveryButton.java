package com.limbwal.adam.physicstool;

import android.text.Html;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.limbwal.adam.physicstool.R;

import static com.limbwal.adam.physicstool.R.id.AngleType;


class EveryButton {
    static boolean degree = true;

    static void proceed(EditText editText, int start, View view) {
        String previousCharacter = "";
        String prev2Character = "";
        String prev3Character = "";
        boolean notANumber = true;
        boolean doNot = false;
        String ScreenText = editText.getText().toString();
        try {
            previousCharacter = "" + ScreenText.charAt(start - 1);

        } catch (StringIndexOutOfBoundsException ignored) {
        }
        try {
            prev2Character = "" + ScreenText.charAt(start - 2);
        } catch (StringIndexOutOfBoundsException ignored) {
        }
        try {
            prev3Character = "" + ScreenText.charAt(start - 3);
        } catch (StringIndexOutOfBoundsException ignored) {
        }
        switch (view.getId()) {
            case R.id.num1:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "1");
                    //numberCounter++;
                }
                break;
            case R.id.num2:
                if (!(previousCharacter.equals("³")) && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "2");
                    //numberCounter++;
                }
                break;
            case R.id.num3:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "3");
                    //numberCounter++;
                }
                break;
            case R.id.num4:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "4");
                    //numberCounter++;
                }
                break;
            case R.id.num5:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "5");
                    //numberCounter++;
                }
                break;
            case R.id.num6:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "6");
                    //numberCounter++;
                }
                break;
            case R.id.num7:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "7");
                    //numberCounter++;
                }
                break;
            case R.id.num8:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "8");
                    //numberCounter++;
                }
                break;
            case R.id.num9:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "9");
                    //numberCounter++;
                }
                break;
            case R.id.num0:
                if (!previousCharacter.equals("³") && !previousCharacter.equals("²")) {
                    editText.getText().insert(start, "0");
                    //numberCounter++;
                }
                break;
            case R.id.multi:
                switch (previousCharacter) {
                    case "÷":
                    case "+":
                    case "√":
                    case "×":
                    case "-":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, "×");
                        break;
                }
                break;
            case R.id.division:
                switch (previousCharacter) {
                    case "÷":
                    case "+":
                    case "√":
                    case "×":
                    case "s":
                    case "n":
                    case "-":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, "÷");
                        break;
                }
                break;
            case R.id.plus:
                switch (previousCharacter) {
                    case "÷":
                    case "+":
                    case "s":
                    case "n":
                    case "√":
                    case "×":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, "+");
                        break;
                }
                break;


            case R.id.sqrt:
                for (int cnt = 0; cnt < 10; cnt++) {
                    String num = "" + cnt;
                    if (previousCharacter.equals(num)) {
                        notANumber = false;
                    }
                }
                switch (previousCharacter) {
                    case "√":
                        break;
                    default:
                        if (!notANumber) {
                            editText.getText().insert(start, "×√(");
                            start += 2;
                        } else {
                            editText.getText().insert(start, "√(");
                            start++;
                        }
                        break;
                }
                break;

            case R.id.subtract:
                if (!previousCharacter.equals("√")) {
                    editText.getText().insert(start, "-");
                }
                break;
            case R.id.decimal:
                switch (previousCharacter) {
                    case ".":
                        break;
                    default:
                        editText.getText().insert(start, ".");
                        break;
                }
                break;


            case R.id.back:
                doNot = true;
                if (ScreenText.equals("Error!")) {
                    editText.setText("");
                }
                try {

                    int delLength = editText.getText().length();

                    if (previousCharacter.equals("(") && (prev2Character.equals("n") ||
                            prev2Character.equals("s"))
                            && (prev3Character.equals("o") || prev3Character.equals("i") ||
                            prev3Character.equals("a"))) {

                        editText.getText().delete(start - 4, start);

                    } else if (delLength > 0) {
                        editText.getText().delete(start - 1, start);
                    }
                } catch (IndexOutOfBoundsException ignored) {

                }


                break;

            case R.id.answer:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+") ||
                        previousCharacter.equals("×") || previousCharacter.equals("-") ||
                        previousCharacter.equals("") ||
                        previousCharacter.equals("(")) {
                    editText.getText().insert(start, "ANS");
                    start += 2;
                } else {
                    editText.getText().insert(start, "×ANS");

                    start += 3;
                }
                break;
            case R.id.sin:
                if (previousCharacter.equals("÷")
                        || previousCharacter.equals("+")
                        || previousCharacter.equals("×")
                        || previousCharacter.equals("-")
                        || previousCharacter.equals("")
                        || previousCharacter.equals("(")) {

                    editText.getText().insert(start, "sin(");
                    start += 3;

                }


                break;
            case R.id.cos:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {

                    editText.getText().insert(start, "cos(");
                    start += 3;
                }
                break;
            case R.id.tan:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {

                    editText.getText().insert(start, "tan(");
                    start += 3;
                }
                break;
            case R.id.exponent:
                switch (previousCharacter) {
                    case "÷":
                    case "E":
                    case "-":
                    case "+":
                    case "×":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, "E");

                        break;
                }

                break;

            case R.id.bracket_open:
                if (previousCharacter.equals("÷") || previousCharacter.equals("")
                        || previousCharacter.equals("E") || previousCharacter.equals("-")
                        || previousCharacter.equals("+") || previousCharacter.equals("×")
                        || previousCharacter.equals("s") || previousCharacter.equals("n")
                        || previousCharacter.equals("(")) {
                    editText.getText().insert(start, "(");
                }
                else {
                    editText.getText().insert(start, "×(");
                    start++;
                }
                break;
            case R.id.bracket_closed:
                switch (previousCharacter) {
                    case "÷":
                    case "E":
                    case "-":
                    case "+":
                    case "×":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, ")");
                        break;
                }
                break;
            case R.id.square:
                switch (previousCharacter) {
                    case "÷":
                    case "":
                    case "²":
                    case "E":
                    case "-":
                    case "+":
                    case "×":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:

                        editText.getText().insert(start, "²");
                        start++;
                        break;
                }
                break;


            case R.id.toThePowerOf:
                switch (previousCharacter) {
                    case "÷":
                    case "":
                    case "E":
                    case "-":
                    case "+":
                    case "×":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, "^");
                        break;
                }
                break;


            case R.id.xRoot:
                switch (previousCharacter) {
                    case "÷":
                    case "":
                    case "E":
                    case "-":
                    case "+":
                    case "×":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:
                        editText.getText().delete(start - 1, start);
                        start--;
                        editText.getText().insert(start, Html.fromHtml("<sup><small>" + previousCharacter + "</small></sup>√("));
                        start+=2;

                        break;
                }
                break;
            case R.id.pi:
                if (previousCharacter.equals("÷") || previousCharacter.equals("")
                        || previousCharacter.equals("E") || previousCharacter.equals("-")
                        || previousCharacter.equals("+") || previousCharacter.equals("×")
                        || previousCharacter.equals("s") || previousCharacter.equals("n")
                        || previousCharacter.equals("(")) {

                    editText.getText().insert(start, "π");
                } else {
                    editText.getText().insert(start, "×π");
                    start++;
                }
                break;
            case R.id.factorial:
                switch (previousCharacter) {
                    case "÷":
                    case "":
                    case "E":
                    case "-":
                    case "+":
                    case "×":
                    case "s":
                    case "n":
                    case "(":

                        break;
                    default:
                        editText.getText().insert(start, "!");

                        break;
                }
                break;

            case R.id.log:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, "log(");

                    start += 4;
                } else {
                    editText.getText().insert(start, "×log(");

                    start += 5;
                }
                break;
            case R.id.naturalLog:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, "ln(");
                    start += 3;
                } else {
                    editText.getText().insert(start, "×ln(");
                    start += 4;
                }
                break;
            case R.id.inverseCos: // inverse cos
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, Html.fromHtml("cos<sup><small>-1</small></sup>" + "("));
                    start += 5;

                }
                break;
            case R.id.inverseSin:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, Html.fromHtml("sin<sup><small>-1</small></sup>" + "("));
                    start += 5;

                }
                break;


            case R.id.inverseTan:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {

                    editText.getText().insert(start, Html.fromHtml("tan<sup><small>-1</small></sup>" + "("));
                    start += 5;

                }
                break;

            case R.id.inverseLog:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, "10^");
                    start += 2;
                } else {
                    editText.getText().insert(start, "×10^");
                    start += 3;
                }
                break;
            case R.id.inverseNaturalLog:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, "e^");
                    start++;
                } else {
                    editText.getText().insert(start, "×e^");
                    start += 2;
                }
                break;

            case R.id.numberE:
                if (previousCharacter.equals("÷") || previousCharacter.equals("+")
                        || previousCharacter.equals("×") || previousCharacter.equals("-")
                        || previousCharacter.equals("") || previousCharacter.equals("(")) {
                    editText.getText().insert(start, "e");

                } else {
                    editText.getText().insert(start, "×e");
                    start++;
                }
                break;

        }
        try {


            if (doNot) {
                editText.setSelection(start - 1);
            } else {
                editText.setSelection(start + 1);
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        //workspace.vibrate();
        //workspace.setNumSize();
    }
}
