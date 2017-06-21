package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/03/30.
 */

public class WidgetDemoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget);

        Button btn1 = (Button) findViewById(R.id.widget_btn1);
        Button btn2 = (Button) findViewById(R.id.widget_btn2);
        Button btn3 = (Button) findViewById(R.id.widget_btn3);
        Button btn4 = (Button) findViewById(R.id.widget_btn4);
        Button btn5 = (Button) findViewById(R.id.widget_btn5);
        Button btn6 = (Button) findViewById(R.id.widget_btn6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WidgetDemoActivity.this,RegisterActivity.class);
                WidgetDemoActivity.this.startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WidgetDemoActivity.this,SchoolInformation.class);
                WidgetDemoActivity.this.startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WidgetDemoActivity.this,BoxActivity.class);
                WidgetDemoActivity.this.startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WidgetDemoActivity.this,ImageActivity.class);
                WidgetDemoActivity.this.startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WidgetDemoActivity.this,LayoutActivity.class);
                WidgetDemoActivity.this.startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WidgetDemoActivity.this,CalculatorActivity.class);
                WidgetDemoActivity.this.startActivity(intent);
            }
        });

    }

}
