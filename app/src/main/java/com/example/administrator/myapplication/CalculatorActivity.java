package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class CalculatorActivity extends Activity implements View.OnClickListener {

    private static String first; //最先输入的数字

    private static String second;//输入符号的数字

    private static String operator;

    private TextView textView;

    private String text;

    private int[] Ids = {R.id.cal_one, R.id.cal_two, R.id.cal_three, R.id.cal_four, R.id.cal_five,
            R.id.cal_six, R.id.cal_seven, R.id.cal_eight, R.id.cal_nine, R.id.cal_zero, R.id.cal_plus,
            R.id.cal_sub, R.id.cal_multiply, R.id.cal_div, R.id.cal_sign, R.id.cal_dot, R.id.cal_remain,
            R.id.cal_ac, R.id.cal_equal};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //为控件批量添加监听器
        for (int i = 0; i < Ids.length; i++) {
            findViewById(Ids[i]).setOnClickListener(this);
        }

        textView = (TextView) findViewById(R.id.cal_textview);

    }

    @Override
    public void onClick(View v) {
        text = textView.getText().toString();
        switch (v.getId()) {
            case R.id.cal_zero:
                text += "0";
                break;
            case R.id.cal_one:
                text += "1";
                break;
            case R.id.cal_two:
                text += "2";
                break;
            case R.id.cal_three:
                text += "3";
                break;
            case R.id.cal_four:
                text += "4";
                break;
            case R.id.cal_five:
                text += "5";
                break;
            case R.id.cal_six:
                text += "6";
                break;
            case R.id.cal_seven:
                text += "7";
                break;
            case R.id.cal_eight:
                text += "8";
                break;
            case R.id.cal_nine:
                text += "9";
                break;
            case R.id.cal_plus:
                operator = "1";
                first = textView.getText().toString();
                text = "";
                break;
            case R.id.cal_sub:
                first = textView.getText().toString();
                text = "";
                operator = "2";
                break;
            case R.id.cal_multiply:
                first = textView.getText().toString();
                operator = "3";
                text = "";
                break;
            case R.id.cal_div:
                first = textView.getText().toString();
                operator = "4";
                text = "";
                break;
            case R.id.cal_dot:
                text += ".";
                break;
            case R.id.cal_remain:
                first = textView.getText().toString();
                text = "";
                operator = "5";
                break;
            case R.id.cal_ac:
                text = "";
                break;
            case R.id.cal_sign:
                if (Integer.parseInt(text) > 0) {
                    text = "-" + text;
                } else {
                    text = text.replace("-", "");
                }
                break;
            case R.id.cal_equal:
                second = text;
                Double x = Double.valueOf(first);
                Double y = Double.valueOf(second);
                if (operator.equals("1")) {
                    text = String.valueOf((x + y));
                    operator = "0";
                } else if (operator.equals("2")) {
                    text = String.valueOf((x - y));
                    operator = "0";
                } else if (operator.equals("3")) {
                    text = String.valueOf((x * y));
                    operator = "0";
                } else if (operator.equals("4") && y != 0) {
                    double s = x / y;
                    //DecimalFormat decimalFormat = new DecimalFormat("##########,####");
                    //保留四位小数
                    text = String.format("%.4f",s);
                    operator = "0";
                } else if (operator.equals("4") && y == 0) {
                    Toast.makeText(this, "除数为0", Toast.LENGTH_SHORT).show();
                    text = "";
                    operator = "0";
                } else if (operator.equals("5")) {
                    text = String.valueOf((x % y));
                    operator = "0";
                } else {
                    text = "x";
                }
                break;
            default:
                break;
        }
        textView.setText(text);
    }

}
