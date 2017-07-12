package com.limbwal.adam.physicstool;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class equals  {
    private static workspace workspace;

    public equals(workspace activity) {
        this.workspace = activity;
    }
    static int mc = 10;
    static final char s_multiplication = '×';
    static final char s_division = '÷';
    static final char s_sqrt = '√';
    static final char s_pi = 'π';
    static boolean degree = true;
    static BigDecimal answer = new BigDecimal(1);

    // final char s_separator = '˼';
    public static String calculate(String text) {
        degree = true;
        if (workspace.angleTypeCounter%2!=0){
            degree = false;
        }
        try {
            text = findAndReplacePi(text);
            text = findAndReplaceTrig(text);
            text = findAndReplaceAnswer(text);
            text = findAndReplaceExponent(text);
            text = findAndReplaceNumberE(text);
            text = findBrackets(text);
            text = findAndReplaceTrig(text);
            text = squareRoot(text);
            text = raiseToSquare(text);

            text = calcLog(text);
            text = calcLN(text);
            text = raiseToExponent(text);
            if (text.equals("Error!")) {
                return text;
            }
            text = multiplyAndDivide(text);
            text = addAndSubtract(text);

            text = removeTrailingZeros(text);

            try {
                answer = new BigDecimal(text);
            }
            catch (NumberFormatException e){
                answer = new BigDecimal(1);
            }

            }
             catch (NumberFormatException e){
                 return "Error!";
             }
            return text;
        }
    public static String findBrackets(String text) {
        System.out.println(text);
        int length = text.length();
        char currentChar;
        String textBeforeBracket = "";
        String textAfterBracket = "";
        String bufferText = "";
        int cnt;
        for (cnt = length - 1; cnt >= 0; cnt--) {
            if (text.charAt(cnt) == '(') {
                for (int cnt2 = 0; cnt2 < cnt; cnt2++) {
                    textBeforeBracket += text.charAt(cnt2);
                }
                break;
            }
            if (cnt == 0 && text.charAt(cnt) != '(') {
                return text;
            }
        }
        boolean foundBracket = false;
        for (int forward = cnt + 1; forward < length; forward++) {
            currentChar = text.charAt(forward);
            if (foundBracket) {
                textAfterBracket += currentChar;
            } else {

                if (currentChar == ')') {

                    foundBracket = true;
                } else {
                    bufferText += currentChar;
                }
            }
        }
        if (foundBracket) {
            bufferText = calculate(bufferText);
        }
        foundBracket = false;
        for (cnt = 0; cnt < textBeforeBracket.length(); cnt++) {
            if (text.charAt(cnt) == '(') {
                foundBracket = true;
            }
        }
        if (foundBracket) {
            return findBrackets(textBeforeBracket + bufferText + textAfterBracket);
        } else {
            return textBeforeBracket + bufferText + textAfterBracket;
        }

    }
    public static String addAndSubtract(String text) {

        return findTerms(text, '+', '-');
    }
    public static String multiplyAndDivide(String text) {

        return findTerms(text, s_multiplication, s_division);
    }
    public static String findTerms(String text, char character1, char character2) {
        String firstNumber = "";
        String secondNumber = "";
        String end = "";
        String beginning = "";
        char currentChar;
        char character = 'g';
        int cnt;
        int start = 0;
        boolean foundEnd = false;
        boolean foundmult = false;
        for (cnt = 0; cnt < text.length(); cnt++) {
            currentChar = text.charAt(cnt);
            if (foundmult) {
                if (foundEnd) {
                    end += currentChar;
                } else {

                    if (currentChar == s_multiplication || currentChar == s_division ||
                            currentChar == '+' ||
                            (currentChar == '-'&&cnt!=0 &&
                                    text.charAt(cnt-1)!=s_multiplication&&
                                    text.charAt(cnt-1)!=s_division &&
                                    text.charAt(cnt-1)!='+')) {

                        foundEnd = true;
                        end += currentChar;

                    } else {
                        secondNumber += currentChar;
                    }
                }
            } else {

                if (currentChar == character1 || (currentChar == character2 && cnt!=0 &&
                        text.charAt(cnt-1)!=s_multiplication&&
                        text.charAt(cnt-1)!=s_division &&
                        text.charAt(cnt-1)!='+')) {
                    character = currentChar;
                    foundmult = true;
                    start = cnt;
                }
            }


        }
        if (!foundmult) {

            return text;
        }
        foundEnd = false;

        for (int cnt2 = start - 1; cnt2 >= 0; cnt2--) {
            currentChar = text.charAt(cnt2);


            if (foundEnd) {
                beginning += currentChar;
            } else {
                if (currentChar == s_multiplication || currentChar == s_division ||
                        currentChar == '+'||
                        (currentChar == '-'&&cnt2!=0 &&
                                text.charAt(cnt2-1)!=s_multiplication&&
                                text.charAt(cnt2-1)!=s_division &&
                                text.charAt(cnt2-1)!='+')) {


                    foundEnd = true;
                    beginning += currentChar;

                } else {
                    firstNumber += currentChar;
                }
            }
        }


        firstNumber = revString(firstNumber);
        beginning = revString(beginning);
        BigDecimal first = new BigDecimal(firstNumber);
        BigDecimal second = new BigDecimal(secondNumber);

        switch (character) {
            case s_multiplication:
                if (parse(end, s_multiplication, s_division)) {
                    text = multiplyAndDivide(beginning + first.multiply(second) + end);
                } else {
                    text = beginning + first.multiply(second) + end;
                }
                break;
            case s_division:
                if (parse(end, s_multiplication, s_division)) {
                    text = multiplyAndDivide(beginning + first.divide(second, mc, RoundingMode.HALF_UP) + end);
                } else {
                    text = beginning + first.divide(second, mc, RoundingMode.HALF_UP) + end;
                }
                break;
            case '+':
                if (parse(end, '+', '-')) {
                    text = addAndSubtract(beginning + first.add(second) + end);
                } else {
                    text = beginning + first.add(second) + end;
                }
                break;
            case '-':
                if (parse(end, '-', '+')) {
                    text = addAndSubtract(beginning + first.subtract(second) + end);
                } else {
                    text = beginning + first.subtract(second) + end;
                }

        }


        return text;
    }
    public static String revString(String text) {
        String newText = "";
        for (int cnt = text.length() - 1; cnt >= 0; cnt--) {
            newText += text.charAt(cnt);
        }
        return newText;
    }
    public static boolean parse(String text, char character, char character2) {
        boolean found = false;
        for (int cnt = 0; cnt < text.length(); cnt++) {
            if (text.charAt(cnt) == character || text.charAt(cnt) == character2) {
                found = true;
                break;
            }
        }
        return found;
    }
    public static String findAndReplacePi(String text){
        String bufferString ="";
        char currentChar;
        for(int cnt=0; cnt<text.length(); cnt++){
            currentChar = text.charAt(cnt);
            if (currentChar==s_pi){
                bufferString+=Math.PI;
            }
            else{
                bufferString+=currentChar;
            }
        }
        return bufferString;
    }
    public static String findAndReplaceNumberE(String text){
        String bufferString ="";
        char currentChar;
        for(int cnt=0; cnt<text.length(); cnt++){
            currentChar = text.charAt(cnt);
            if (currentChar=='e'){
                bufferString+=Math.E;
            }
            else{
                bufferString+=currentChar;
            }
        }
        return bufferString;
    }
    public static String findAndReplaceExponent(String text){
        String[] a = findLocationAndReturnStrings(text, 'E');
        String bufferString;
        String term;
        String end ;
        String beginning;

        if(!a[0].equals("notFound")) {
            bufferString = a[1];
            term = a[2];
            end = a[3];
            beginning = a[0];
        }
        else
        {
            return text;
        }
        return beginning+ "("+ bufferString+ s_multiplication+ "10^(" +term+"))"+end;
    }
    public static String[] findFollowingTerm(String text, int start){
        char nextChar;
        char prevChar;
        String end = "";
        String term = "";
        boolean found = false;
        for (int cnt = start+1; cnt<text.length(); cnt++){
            nextChar = text.charAt(cnt);
            prevChar = text.charAt(cnt-1);


            if (nextChar == s_multiplication || nextChar == s_division ||
                    nextChar == '+'
                    || (nextChar == '-'&&(prevChar!='E'&&prevChar!='^'))) {
                found = true;
            }
            if (found){
                end+=nextChar;
            }
            else{
                term+=nextChar;
            }
        }
        return new String[]{term, end};
    }
    public static String[] findPreviousTerm(String text, int start){
        char currentChar;
        String bufferString = "";
        String beginning = "";
        boolean found = false;
        for (int cnt = start-1; cnt >= 0; cnt--) {
            currentChar = text.charAt(cnt);
            if (currentChar == s_multiplication
                    || currentChar == s_division
                    || currentChar == '+'
                    || currentChar == '-') {
                found = true;
            }
            if (found) {
                beginning += currentChar;
            }
            else  {
                bufferString += currentChar;
            }
        }
        beginning = revString(beginning);
        bufferString = revString(bufferString);
        return new String[]{beginning, bufferString};
    }
    public static String[] findLocationAndReturnStrings(String text, char character){
        String bufferString;
        String term="";
        String end = "";
        String beginning;
        int location=-1;
        char currentChar;
        for(int cnt=0; cnt<text.length(); cnt++) {
            currentChar = text.charAt(cnt);
            if (currentChar == character) {
                location = cnt;
                String[] a = findFollowingTerm(text, cnt);
                term = a[0];
                end = a[1];
            }
        }
        if(location!=-1) {
            String[] a = findPreviousTerm(text, location);
            beginning = a[0];
            bufferString = a[1];
        }
        else
        {
            return new String[]{"notFound", "notFound"};
        }
        return new String[]{beginning, bufferString, term, end};
    }
    public static String raiseToExponent(String text){
        String[] a = findLocationAndReturnStrings(text, '^');
        String bufferString;
        String term;
        String end ;
        String beginning;

        if(!a[0].equals("notFound")) {
            beginning = a[0];
            bufferString = a[1];
            term = a[2];
            end = a[3];
        }
        else
        {
            return text;
        }
        try {

            double c = Double.parseDouble(bufferString);
            double b = Double.parseDouble(term);
            return beginning+new BigDecimal(""+Math.pow(c,b))+end;
        }
        catch (ArithmeticException | NumberFormatException | OutOfMemoryError e){
            return "Error!";
        }
    }
    public static String raiseToSquare(String text){
        String[] a = findLocationAndReturnStrings(text, '²');
        String bufferString;
        String term;
        String end ;
        String beginning;

        if(!a[0].equals("notFound")) {
            beginning = a[0];
            bufferString = a[1];
            term = a[2];
            end = a[3];
        }
        else
        {
            return text;
        }
        try {

            double c = Double.parseDouble(bufferString);

            return beginning+new BigDecimal(""+Math.pow(c,2.0))+end;
        }
        catch (ArithmeticException | NumberFormatException | OutOfMemoryError e){
            return "Error!";
        }
    }
    public static String calcLog(String text){

        String term="";
        String end = "";
        String beginning="";

        char currentChar;
        boolean found = false;
        for(int cnt=0; cnt<text.length(); cnt++) {
            currentChar = text.charAt(cnt);
            if (currentChar == 'l' && text.charAt(cnt + 1) == 'o' && text.charAt(cnt + 2) == 'g') {

                found = true;
                String[] a = findFollowingTerm(text, cnt + 2);
                term = a[0];
                end = a[1];
                break;
            } else {
                beginning += currentChar;
            }
        }
        if (found){
            System.out.println(term);
            double dTerm = Double.parseDouble(term);

            dTerm = Math.log10(dTerm);
            System.out.println(dTerm);
            return beginning+dTerm+end;
        }
        else{
            return text;
        }
    }
    public static String calcLN(String text){

        String term="";
        String end = "";
        String beginning="";

        char currentChar;
        boolean found = false;
        for(int cnt=0; cnt<text.length(); cnt++) {
            currentChar = text.charAt(cnt);
            if (currentChar == 'l' && text.charAt(cnt + 1) == 'n') {

                found = true;
                String[] a = findFollowingTerm(text, cnt + 1);
                term = a[0];
                end = a[1];
                break;
            } else {
                beginning += currentChar;
            }
        }
        if (found){
            System.out.println(term);
            double dTerm = Double.parseDouble(term);

            dTerm = Math.log(dTerm);
            System.out.println(dTerm);
            return beginning+dTerm+end;
        }
        else{
            return text;
        }
    }
    public static String squareRoot(String text){
        String[] a = findLocationAndReturnStrings(text, s_sqrt);
        String bufferString;
        String term;
        String end ;
        String beginning;
        if(!a[0].equals("notFound")) {
            beginning = a[0];
            bufferString = a[1];
            term = a[2];
            end = a[3];
        }
        else
        {
            return text;
        }
        if (bufferString==""){
            bufferString="2";//to make sqrt
        }
        try {

            double c = Double.parseDouble(bufferString);

            double b = Double.parseDouble(term);
            return beginning+new BigDecimal(""+Math.pow(b, (1/c)))+end;
        }
        catch (ArithmeticException | NumberFormatException | OutOfMemoryError e){
            return "Error!";
        }
    }
    public static String removeTrailingZeros (String text){
        int length = text.length();
        boolean decimalPresent = false;
        String newString = text;
        if (!(text.equals(""))) {
            for (int cnt = 0; cnt < length; cnt++) {
                if (text.charAt(cnt) == '.') {
                    decimalPresent = true;
                }
            }

            int cnt = length - 1;
            if (decimalPresent) {
                while (text.charAt(cnt) == '0') {
                    cnt--;
                }


            }
            if (text.charAt(cnt) == '.') {
                cnt--;
            }
            newString = "";
            for (int a = 0; a <= cnt; a++) {
                newString += text.charAt(a);
            }
        }
        return newString;
    }

    public static String findAndReplaceAnswer(String text){
        String bufferString ="";
        char currentChar;
        for(int cnt=0; cnt<text.length(); cnt++){
            currentChar = text.charAt(cnt);
            if (currentChar=='A'){
                bufferString+=answer;
                cnt+=2;
            }
            else{
                bufferString+=currentChar;
            }
        }
        return bufferString;
    }
    public static String findAndReplaceTrig(String text){
        String bufferString ="";
        boolean one = parse(text, 's' , 'c');
        boolean two = parse(text, 't', 's');
        if (!(one && two)){
            return text;
        }
        char currentChar;
        boolean inverse;
        String trigValue = "";
        char nextChar;
        char plus3;
        char plus4;
        char plus5;

        try {
            for (int cnt = 0; cnt < text.length(); cnt++) {

                currentChar = text.charAt(cnt);
                plus3 = text.charAt(cnt+3);
                plus4 = text.charAt(cnt+4);
                plus5 = text.charAt(cnt+5);
                if (currentChar == 's'||currentChar =='c'||currentChar=='t'){
                    if (plus3 == '('){
                        inverse = false;
                        cnt += 4;
                        for (int cnt2 = cnt; cnt2 < text.length(); cnt2++){
                            nextChar = text.charAt(cnt2);
                            if (nextChar==')'||cnt2==text.length()-1){
                                if (cnt2 == text.length()-1 && nextChar!=')')
                                    trigValue+=nextChar;
                                String newString = trigCalc(trigValue, inverse, currentChar);
                                bufferString += newString;
                                break;
                            }
                            else {
                                trigValue+=nextChar;
                            }
                        }
                    }
                    else if (plus3 =='-'&&plus4=='1'){
                        inverse = true;
                        cnt+=6;
                        for (int cnt2 = cnt; cnt2 < text.length(); cnt2++){
                            nextChar = text.charAt(cnt2);
                            if (nextChar==')'||cnt2==text.length()-1){
                                if (cnt2 == text.length()-1 && nextChar!=')')
                                    trigValue+=nextChar;
                                String newString = trigCalc(trigValue, inverse, currentChar);
                                bufferString += newString;
                                break;
                            }
                            else {
                                trigValue+=nextChar;
                            }
                        }
                    }
                    else{
                        return "Error!";
                    }

                } else {
                    bufferString += currentChar;
                }
            }
            return bufferString;
        }
        catch (StringIndexOutOfBoundsException e) {
            return bufferString;
        }
    }
    public static String trigCalc(String text, boolean inverse, char trig){
        //text = calculate(text);
        double a = Double.parseDouble(text);

        if (inverse){
            System.out.println("came");
            switch (trig){
                case 's':
                    a = Math.asin(a);
                    break;
                case 'c':
                    a = Math.acos(a);
                    break;
                case 't':
                    a = Math.atan(a);
                    break;
            }
            System.out.println(a);
            if (degree){
                a = Math.toDegrees(a);
            }
        }
        else{
            if (degree){
                a = Math.toRadians(a);
            }
            switch (trig){
                case 's':
                    a = Math.sin(a);
                    break;
                case 'c':
                    a = Math.cos(a);
                    break;
                case 't':
                    a = Math.tan(a);
                    break;
            }
        }
        BigDecimal value = new BigDecimal(a);
        value = value.divide(new BigDecimal(2.0), 9, RoundingMode.HALF_UP);
        value = value.multiply(new BigDecimal(2.0));
        return ""+value;
    }


}




