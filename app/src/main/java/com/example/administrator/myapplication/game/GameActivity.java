package com.example.administrator.myapplication.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2017/05/24.
 */

public class GameActivity extends Activity {

    //public final static int PLAYER_ARRIVED = 1;

    //游戏开始时，自动播放背景音乐
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(GameActivity.this, GameBGMService.class);
        startService(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //activity无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //activity全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameSurface(this));
        //setContentView(R.layout.gameview);
    }

    //游戏停止时，自动停止背景音乐
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(GameActivity.this, GameBGMService.class);
        stopService(intent);
    }
}
