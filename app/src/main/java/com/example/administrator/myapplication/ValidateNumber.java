package com.example.administrator.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/05/11.
 */

public class ValidateNumber extends BroadcastReceiver {

    private String content = "";
    private String address = "";
    private String TAG = "TAG";

    @Override
    public void onReceive(Context context, Intent intent) {
        //当接收到广播时就执行这个，但是如果能具体确认接收到的广播是短信的话会更好
        //在android manifest文件中已经注明intent filter 过滤广播
        //从这里获取短信的编码方式 应该是GSM 或者CDMA之类的
        //String format = intent.getStringExtra("format");
        Calendar c = Calendar.getInstance();
        Object[] objects = (Object[]) intent.getExtras().get("pdus");

        for (Object o : objects) {
            byte[] pdu = (byte[]) o;

            //如果API>=23 就需要使用两个参数的createFromPdu
            SmsMessage smsMessage = SmsMessage.createFromPdu(pdu);

            //将获取到的内容循环相加，这样当短信内容过长时，不会导致前面的短信内容缺失
            content += smsMessage.getMessageBody();
            //如果要在Edittext上面直接给出验证码，那么你必须要明确你发送验证短信的格式，
            // 验证码是在第几位到第几位

            //这里用来显示收到短信的时间
            c.setTimeInMillis(smsMessage.getTimestampMillis());
            address = smsMessage.getOriginatingAddress();
        }
        //Log.i(TAG, content);
    }

    public String getContent() {
        return content;
    }

    public String getAddress() {
        return address;
    }
}

