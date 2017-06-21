package com.example.administrator.myapplication.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/05/24.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "MOVE";
    public static boolean IS_ARRIVED = false;
    Context context;
    SurfaceHolder holder;            //SurfaceView的管理器
    Canvas canvas;                    //声明画布和画笔
    Paint paint;
    boolean flag;                //控制线程的标志位
    //游戏元素
    Player player;
    Bitmap bp;
    private boolean runFlag;
    private int x = 20, y = 20; //touch 事件的坐标 让它一开始不等于人物的坐标


    //自定义View控件，必须提供Context参数的构造方法
    public GameSurface(Context context) {
        super(context);
        //super(context);
        this.context = context;
        holder = getHolder();            //初始化holder
        holder.addCallback(this);    //给holder添加回调接口
        paint = new Paint();            //创建画笔对象
        setFocusable(true);
        bp = BitmapFactory.decodeResource(getResources(), R.drawable.pic4);
    }

    //SurfaceView发生变化时调用
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }


    //SurfaceView创建时调用
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //创建人物
        player = new Player(this);
        //先调用一次myDraw 让画面出现
        myDraw();
        //刚开始让线程开始运行
        flag = true;
        //设置标志为false 一开始让人物静止
        runFlag = false;
        //启动线程
        new Thread(this).start();
    }

    //SurfaceView销毁时调用
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;        //SurfaceView视图销毁时，子线程停止
    }

    //自定义绘图方法
    public void myDraw() {
        //获取画布对象
        canvas = holder.lockCanvas();
        if (canvas != null) {
            //刷屏
            canvas.drawColor(Color.GRAY);
            //canvas.drawBitmap(bp, 0, 0, paint);
            //游戏元素的绘制
            player.draw(canvas, paint);
            //所有绘制程序都执行完成后，需要释放canvas
            holder.unlockCanvasAndPost(canvas);
        }
    }

    //线程
    @Override
    public void run() {
        //标志位为true，线程一直运行
        while (flag) {
            arrived();
            if (IS_ARRIVED) {
                myDraw();
                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (runFlag) {
                long st = System.currentTimeMillis();
                //游戏逻辑，以及绘图代码
                myDraw();
                player.move();
                long et = System.currentTimeMillis();
                //实现定时刷新游戏界面，间隔80ms
                if (et - st < 100) {
                    try {
                        Thread.sleep(100 - (et - st));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //检测是否到达触屏位置，如果到达，让人物停止
    //由于不可能完全坐标相同，这里我们应该设置一个差值 在这个误差范围内我们都认定它到达了
    //经测试 y每一次移动是5个单位 所以 我们认为在5个单位以内都算到达
    public void arrived() {
        if (Math.abs(y - player.py) < 5) {
            if (Math.abs(x - player.px) < 5) {
                runFlag = false;
                IS_ARRIVED = true;
            } else if (x > player.px) {
                player.makeDir(Player.Direction.RIGHT);
            } else if (x < player.px) {
                player.makeDir(Player.Direction.LEFT);
            }
        } else if (y > player.py) {
            player.makeDir(Player.Direction.DOWN);
        } else if (y < player.py) {
            player.makeDir(Player.Direction.UP);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = (int) event.getX();
        y = (int) event.getY();
        runFlag = true;
        //Log.i(TAG, "x,y");
        //arrived();

//        int x1 = player.px - x;
//        int y1 = player.py - y;
//        if (x > player.px && (Math.abs(x1) > Math.abs(y1))) {
//            player.makeDir(Player.Direction.RIGHT);
//            runFlag = true;
//            arrived();
//        } else if (x < player.px && (Math.abs(x1) > Math.abs(y1))) {
//            player.makeDir(Player.Direction.LEFT);
//            runFlag = true;
//            arrived();
//        } else if (y > player.py && (Math.abs(x1) < Math.abs(y1))) {
//            player.makeDir(Player.Direction.DOWN);
//            runFlag = true;
//            arrived();
//        } else if ((y < player.py && (Math.abs(x1) < Math.abs(y1)))) {
//            player.makeDir(Player.Direction.UP);
//            runFlag = true;
//            arrived();
//        }
        //有点击事件，就可以将线程开启


        //触屏监听

        return true;
    }

    //按键监听  现在基本不用做按键监听
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
            player.makeDir(Player.Direction.DOWN);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
            player.makeDir(Player.Direction.LEFT);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            player.makeDir(Player.Direction.RIGHT);
        } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
            player.makeDir(Player.Direction.UP);
        }
        return super.onKeyUp(keyCode, event);
    }

}
