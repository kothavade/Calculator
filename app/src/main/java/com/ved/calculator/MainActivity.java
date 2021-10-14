package com.ved.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                break;
            case R.id.two:
                numbers+="2";
                break;
            case R.id.three:
                numbers+="3";
                break;
            case R.id.four:
                numbers+="4";
                break;
            case R.id.five:
                numbers+="5";
                break;
            case R.id.six:
                numbers+="6";
                break;
            case R.id.seven:
                numbers+="7";
                break;
            case R.id.eight:
                numbers+="8";
                break;
            case R.id.nine:
                numbers+="9";
                break;
            case R.id.zero:
                numbers+="0";
                break;
            case R.id.clear:
                numbers+="c";
                break;
            case R.id.multiply:
                numbers+="*";
                break;
            case R.id.divide:
                numbers+="/";
                break;
            case R.id.plus:
                numbers+="+";
                break;
            case R.id.minus:
                numbers+="-";
                break;
            case R.id.equal:
                numbers+="=";
                break;
            default:
                 break;
        }

    }
}