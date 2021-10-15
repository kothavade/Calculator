package com.ved.calculator;

import static android.text.TextUtils.split;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String numbers;
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

        numbers="";

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                numbers+="1";
                displayUpdate();
                break;
            case R.id.two:
                numbers+="2";
                displayUpdate();
                break;
            case R.id.three:
                numbers+="3";
                displayUpdate();
                break;
            case R.id.four:
                numbers+="4";
                displayUpdate();
                break;
            case R.id.five:
                numbers+="5";
                displayUpdate();
                break;
            case R.id.six:
                numbers+="6";
                displayUpdate();
                break;
            case R.id.seven:
                numbers+="7";
                displayUpdate();
                break;
            case R.id.eight:
                numbers+="8";
                displayUpdate();
                break;
            case R.id.nine:
                numbers+="9";
                displayUpdate();
                break;
            case R.id.zero:
                numbers+="0";
                displayUpdate();
                break;
            case R.id.clear:
                numbers="";
                displayUpdate();
                break;
            case R.id.multiply:
                numbers+=" *";
                displayUpdate();
                break;
            case R.id.divide:
                numbers+=" /";
                displayUpdate();
                break;
            case R.id.plus:
                numbers+=" +";
                displayUpdate();
                break;
            case R.id.minus:
                numbers+=" -";
                displayUpdate();
                break;
            case R.id.equal:
                calculate();
                displayUpdate();
                numbers="";
                break;
            default:
                 break;
        }

    }
        // 56 +7 *9 -8
        //0 56
        //1 +7
        //2 *9
        //3 -8
    public void displayUpdate(){
        display.setText(numbers);
    }

    public ArrayList<String>  splitNumbers(String input){
        ArrayList<String> splitArr = new ArrayList<>() ;
        StringTokenizer st = new StringTokenizer(input);
        while (st.hasMoreTokens()) {
            splitArr.add(st.nextToken());
        }
        return splitArr;
    }
    /*            double temp;
            String current = stringArray[i];
            String prev = stringArray[i-1];
            char operator = current.charAt(0);
            char prevOperator = prev.charAt(0)
            current=current.substring(1);
            prev = prev.substring(1);
            double currentNum = parseInt(current);
            double prevNum = parseInt(prev); */
    /*if(operator==('*')){
                 temp = currentNum*prevNum;
                 removeTheElement(stringArray, i);
                 stringArray[i-1]=String.format("%f", temp);
            }
            if(operator==('/')){
                temp = currentNum/prevNum;
                removeTheElement(stringArray, i);
                stringArray[i-1]=String.format("%f", temp);
            }
            if(operator==('+')){
                temp = currentNum*prevNum;
                removeTheElement(stringArray, i);
                stringArray[i-1]=String.format("%f", temp);
            }
            if(operator==('-')){
                temp = currentNum*prevNum;
                removeTheElement(stringArray, i);
                stringArray[i-1]=String.format("%f", temp);
            }*/
    // 4 - 3 * 5
    // 4   3   5
    //     -   *
    // 4   15
    //     -
        public void calculate(){
            clean
            ArrayList<String> stringArray = splitNumbers(numbers);
            System.out.println(stringArray);
            ArrayList<Character> operatorArray;
            operatorArray = new ArrayList<>();
            operatorArray.add('0');
            ArrayList<Double> numberArray;
            numberArray = new ArrayList<>();
            numberArray.add(0,parseDouble(stringArray.get(0)));
           for (int i = 1; i < stringArray.size(); i++) {
               String current = stringArray.get(i);
               char operator = current.charAt(0);
               operatorArray.add(i,operator);
               current = current.substring(1);
               numberArray.add(i,parseDouble(current));
           }
            for (int i = 0; i < numberArray.size()-1; i++) {
                if(operatorArray.get(i+1)=='*'){
                    numberArray.set(i, numberArray.get(i)*numberArray.get(i+1));
                    numberArray.remove(i+1);
                    operatorArray.remove(i+1);
                }
            }
            for (int i = 0; i < numberArray.size()-1; i++) {
                if(operatorArray.get(i+1)=='/'){
                    numberArray.set(i, numberArray.get(i)/numberArray.get(i+1));
                    numberArray.remove(i+1);
                    operatorArray.remove(i+1);
                }
            }
            for (int i = 0; i < numberArray.size()-1; i++) {
                if(operatorArray.get(i+1)=='+'){
                    numberArray.set(i, numberArray.get(i)+numberArray.get(i+1));
                    numberArray.remove(i+1);
                    operatorArray.remove(i+1);
                }
            }
            for (int i = 0; i < numberArray.size()-1; i++) {
                if(operatorArray.get(i+1)=='-'){
                    numberArray.set(i, numberArray.get(i)-numberArray.get(i+1));
                    numberArray.remove(i+1);
                    operatorArray.remove(i+1);
                }
            }
            double result = numberArray.get(0);
            numbers = String.format ("%.3f", result);
        }
    }



}