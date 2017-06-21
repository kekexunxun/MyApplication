package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/05/03.
 */

public class LocalService extends Activity {

    private TextView setNum;
    private Button bindAIDLService;
    private Button unBindAIDLService;
    private Button getAIDLNumber;
    private final String TAG ="TAG";

    private getNumAidlService getNumber = null;

    //private getNumAidlService

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            getNumber = getNumAidlService.Stub.asInterface(service);
            setNum.setText("ok");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            getNumber = null;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_localservice);
        initWidget();
        bindAIDLService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalService.this, RandomNumService.class);
                bindService(intent, conn, Service.BIND_AUTO_CREATE);
                Log.i(TAG, "onClick: ");
            }
        });

        unBindAIDLService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        getAIDLNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getNumber == null) {
                    setNum.setText("Service not connected.");
                } else {
                    try {
                        setNum.setText("From Aidl Service：" + getNumber.getNum() + "");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    private void initWidget() {
        setNum = (TextView) findViewById(R.id.tv_getNumber);
        bindAIDLService = (Button) findViewById(R.id.bind_aidl_service);
        unBindAIDLService = (Button) findViewById(R.id.unbind_service);
        getAIDLNumber = (Button) findViewById(R.id.getNumber);
    }
}
