package com.example.administrator.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/04/29.
 */

public class bindCountService extends Service {
    private static int COUNT = 0;
    private myBinder mybinder = new myBinder();
    private TimerTask timerTask = null;
    private Timer timer = new Timer();

    @Override
    public IBinder onBind(Intent intent) {
        return mybinder;
    }

    @Override
    public void onCreate() {
        startCount();
    }

    public void startCount() {
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    COUNT++;
                }
            };
            timer.schedule(timerTask, 1000, 1000);
        }
    }

    public int getCount() {
        return COUNT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopCount();
    }

    public void stopCount() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }


    //在Activity中获取bindCountService实例
    class myBinder extends Binder {
        bindCountService getService() {
            return bindCountService.this;
        }
    }
}
