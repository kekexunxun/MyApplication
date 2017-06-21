package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/04/18.
 */

public class LayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        Button btn1 = (Button) findViewById(R.id.layout_btn1);
        Button btn2 = (Button) findViewById(R.id.layout_btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LayoutActivity.this,LinearLayoutActivity.class);
                LayoutActivity.this.startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LayoutActivity.this,RelativeLayoutActivity.class);
                LayoutActivity.this.startActivity(intent);
            }
        });
    }
}
