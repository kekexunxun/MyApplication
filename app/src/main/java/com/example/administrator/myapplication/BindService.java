package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/04/29.
 */

public class BindService extends Activity {

    private bindCountService bcs;

    private Button serv_Start;
    private Button serv_Stop;
    private Button serv_getNum;
    private TextView getNum;


    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bcs = ((bindCountService.myBinder) service).getService();
            getNum.setText("Service Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bcs = null;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        bindservice();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bindservice);

        serv_Start = (Button) findViewById(R.id.serv_start);
        serv_Stop = (Button) findViewById(R.id.serv_Stop);
        serv_getNum = (Button) findViewById(R.id.serv_getNum);
        getNum = (TextView) findViewById(R.id.serv_tv_getNum);

        serv_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bcs.startCount();
                getNum.setText("Count Started");
            }
        });

        serv_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bcs.stopCount();
                unbindService(conn);
                getNum.setText("Count Stopped");
            }
        });
        serv_getNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNum.setText("The Num is :" + bcs.getCount());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    private void bindservice() {
        Intent intent = new Intent(BindService.this, bindCountService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}

