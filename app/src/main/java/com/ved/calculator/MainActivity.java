package com.ved.calculator;

import static java.lang.Character.isDigit;
import static java.lang.Double.parseDouble;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String input;
    TextView display;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    Button clear;
    Button equal;
    Button open;
    Button close;
    Button decimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.display);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        zero=findViewById(R.id.zero);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        multiply=findViewById(R.id.multiply);
        divide=findViewById(R.id.divide);
        clear=findViewById(R.id.clear);
        equal=findViewById(R.id.equal);
        open=findViewById(R.id.open);
        close=findViewById(R.id.close);
        decimal=findViewById(R.id.decimal);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        clear.setOnClickListener(this);
        equal.setOnClickListener(this);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        decimal.setOnClickListener(this);

        input ="";

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                input +="1";
                displayUpdate();
                break;
            case R.id.two:
                input +="2";
                displayUpdate();
                break;
            case R.id.three:
                input +="3";
                displayUpdate();
                break;
            case R.id.four:
                input +="4";
                displayUpdate();
                break;
            case R.id.five:
                input +="5";
                displayUpdate();
                break;
            case R.id.six:
                input +="6";
                displayUpdate();
                break;
            case R.id.seven:
                input +="7";
                displayUpdate();
                break;
            case R.id.eight:
                input +="8";
                displayUpdate();
                break;
            case R.id.nine:
                input +="9";
                displayUpdate();
                break;
            case R.id.zero:
                input +="0";
                displayUpdate();
                break;
            case R.id.clear:
                input ="";
                display.setText("0");
                //displayUpdate();
                break;
            case R.id.multiply:
                input += "*";
                displayUpdate();
                break;
            case R.id.divide:
                input +="/";
                displayUpdate();
                break;
            case R.id.plus:
                input +="+";
                displayUpdate();
                break;
            case R.id.minus:
                input +="-";
                displayUpdate();
                break;
            case R.id.open:
                input += "(";
                displayUpdate();
                break;
            case R.id.close:
                input += ")";
                displayUpdate();
                break;
            case R.id.decimal:
                input += ".";
                displayUpdate();
                break;
            case R.id.equal:
                try {
                    calculateOuter();
                } catch (Exception e) {
                    errorDetected();
                }

                break;
            default:
                 break;
        }

    }
    public void displayUpdate(){
        display.setText(input);
    }
    public ArrayList<String>  splitNumbers(String input){
        String[] splitArray = input.split("((?=:|/|\\*|\\+|-)|(?<=:|/|\\*|\\+|-))");
        ArrayList<String> splitArrayList = new ArrayList<String>(Arrays.asList(splitArray));
        return splitArrayList;
    }
    public void calculateOuter(){
        int openIndex = 0;
        System.out.println(input);
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i)=='(') openIndex = i;
            else if (input.charAt(i)==')') {
                String innerInput = input.substring(openIndex+1,i);
                String result = calculateInner(innerInput);
                if (result.length()==0){
                    return;
                }
                String temp = input.substring(0, openIndex) + result;
                if (i < input.length()-1)
                    temp += input.substring(i+1);
                input = temp;
                i=0;
                openIndex = 0;
            }
        }
        input = calculateInner(input);
        if (input.length()!=0)
            displayUpdate();
    }
    public String calculateInner(String subinput){
        ArrayList<String> inputArray = splitNumbers(subinput);
        ArrayList<Character> operatorArray = new ArrayList<>();
        ArrayList<Double> numberArray= new ArrayList<>();

        for (int i = 0; i < inputArray.size(); i++) {
            String current = inputArray.get(i);
            if(current.length()>0) {
                if (isDigit(current.charAt(0)) || current.charAt(0)=='.') {
                    numberArray.add(parseDouble(current));
                } else {
                    operatorArray.add(current.charAt(0));
                }
            }
        }
        //error checking
        if (numberArray.size()!=operatorArray.size()+1) {
            errorDetected();
            return input;
        }
        //multiplication and division
        for (int i = 0; i < numberArray.size()-1; i++) {
            if(operatorArray.get(i)=='*' || operatorArray.get(i)=='/' ){
                if (operatorArray.get(i)=='*'){
                    numberArray.set(i, numberArray.get(i)*numberArray.get(i+1));
                }
                else {
                    if (numberArray.get(i+1)==0){
                        errorDetected();
                        return input;
                    }
                    numberArray.set(i, numberArray.get(i) / numberArray.get(i + 1));
                }
                numberArray.remove(i+1);
                operatorArray.remove(i);
                i--;
            }
        }
        //addition and subtraction
        for (int i = 0; i < numberArray.size()-1; i++) {
            if(operatorArray.get(i)=='+' || operatorArray.get(i)=='-' ){
                if (operatorArray.get(i)=='+'){
                    numberArray.set(i, numberArray.get(i)+numberArray.get(i+1));
                }
                else {
                    numberArray.set(i, numberArray.get(i)-numberArray.get(i + 1));
                }
                numberArray.remove(i+1);
                operatorArray.remove(i);
                i--;
            }
        }
        double result = numberArray.get(0);
        return String.valueOf(result);
    }
    public void errorDetected () {
        input = "";
        display.setText("Invalid input. Try again.");
    }
}