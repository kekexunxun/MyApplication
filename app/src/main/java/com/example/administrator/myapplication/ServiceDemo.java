package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/04/29.
 */

public class ServiceDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicedemo);

        Button btn = (Button) findViewById(R.id.sd_service);
        Button btn1 = (Button) findViewById(R.id.sd_local_service);
        Button btn2 = (Button) findViewById(R.id.sd_SMS_broadcast_receiver);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemo.this, BindService.class);
                ServiceDemo.this.startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemo.this, LocalService.class);
                ServiceDemo.this.startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDemo.this, getValidateNumber.class);
                ServiceDemo.this.startActivity(intent);
            }
        });
    }
}
