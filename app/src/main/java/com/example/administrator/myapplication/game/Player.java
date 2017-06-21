package com.example.administrator.myapplication.game;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.administrator.myapplication.R;

/**
 * Created by Administrator on 2017/05/24.
 */

public class Player {
    //人物坐标，行走分速度
    public int px, py, xspeed, yspeed;
    //人物 位图
    private Bitmap lhch;
    //标志图片序列，每帧图宽、高,当前帧
    private int pr, pc, pw, ph, cf;
    private GameSurface gsv;
    private Direction dir = Direction.STAY;

    public Player(GameSurface gsv) {
        super();
        this.gsv = gsv;
        lhch = BitmapFactory.decodeResource(gsv.getResources(), R.drawable.step1);
        px = 0;
        py = 0;
        xspeed = yspeed = 5;
        pw = lhch.getWidth() / 4;    //每帧图宽度
        ph = lhch.getHeight() / 4;    //每帧图高度
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        if (GameSurface.IS_ARRIVED) {
            Paint mPaint = new Paint();
            mPaint.setColor(Color.GREEN);
            mPaint.setStrokeWidth(3);
            mPaint.setTextSize(40);
            mPaint.setTextAlign(Paint.Align.LEFT);
            canvas.drawText("Arriving !", 300, 500, mPaint);
            int x = pw * (cf % 4);
            int y = ph * pr;
            canvas.clipRect(px, py, px + pw, py + ph);
            canvas.drawBitmap(lhch, px - x, py - y, paint);
        } else {
            //canvas.translate(px, py);
            //计算当前帧的坐标
            int x = pw * (cf % 4);
            int y = ph * pr;
            canvas.clipRect(px, py, px + pw, py + ph);
            canvas.drawBitmap(lhch, px - x, py - y, paint);
            Log.i("S", "当前行、帧 ：" + pr + "," + cf % 4);
            cf++;
        }
        canvas.restore();
    }

    public void move() {
        switch (dir) {
            case DOWN:
                py += yspeed;
                pr = 0;
                break;
            case LEFT:
                px -= xspeed;
                pr = 1;//使用第二行人物序列
                break;
            case RIGHT:
                px += xspeed;
                pr = 2;
                break;
            case UP:
                py -= yspeed;
                pr = 3;
                break;
            case STAY:
                break;
        }
        //禁止越界
        if (px <= 0) {
            px = 0;
        }
        if (px + pw >= gsv.getWidth()) {
            px = gsv.getWidth() - pw;
        }
        if (py <= 0) {
            py = 0;
        }
        if (py + ph >= gsv.getHeight()) {
            py = gsv.getHeight() - ph;
        }
    }

    //该方法会通过触屏调用，确定人物的方向
//    public void makeDir(int x, int y) {
//
//    }

    //指定人物方向
    public void makeDir(Direction d) {
        this.dir = d;
    }

    //移动方向
    public enum Direction {
        DOWN, LEFT, RIGHT, UP, STAY
    }

}

