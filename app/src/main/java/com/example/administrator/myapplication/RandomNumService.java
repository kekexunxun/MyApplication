package com.example.administrator.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by Administrator on 2017/05/11.
 */

public class RandomNumService extends Service {

    public class RandomNumber extends getNumAidlService.Stub{

        @Override
        public int getNum() throws RemoteException {
            return (int) (Math.random()*100);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new RandomNumber();
    }
}
