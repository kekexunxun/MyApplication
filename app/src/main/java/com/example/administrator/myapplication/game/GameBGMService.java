package com.example.administrator.myapplication.game;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/05/25.
 */

public class GameBGMService extends Service {
    //private MediaPlayer mp = new MediaPlayer();
    private MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        //如果使用create方法创造MediaPlayer 那么调用是就不需要调用.prepare()方法
        mp = MediaPlayer.create(this,R.raw.skycity);
        mp.setLooping(true);
        mp.start();
    }

    @Override
    public void onDestroy() {
        mp.stop();
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
