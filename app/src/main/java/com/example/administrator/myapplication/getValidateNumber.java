package com.example.administrator.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/05/11.
 */

public class getValidateNumber extends Activity {

    private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private EditText validateNumber;
    private Button getValidateNumber;
    private TextView textView;
    private myBroadCastReceiver SmsReceiver = new myBroadCastReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getvalidatenumber);
        initWidget();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION);
        registerReceiver(SmsReceiver, intentFilter);
        getValidateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当点击按钮时执行这个
                // 实际的话这里我们应该搞一个可以发短信的代码 但是这个需要服务器的支持
                countTime();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(SmsReceiver);
    }

    private void initWidget() {
        validateNumber = (EditText) findViewById(R.id.validate_edittext);
        getValidateNumber = (Button) findViewById(R.id.getValidateNumber);
        textView = (TextView) findViewById(R.id.validate_textview);
    }

    private void countTime() {
        //执行一个倒计时程序 当然这里也可以新开一个线程去执行倒计时
        CountDownTimer countDownTimer = new CountDownTimer(10 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getValidateNumber.setEnabled(false);
                getValidateNumber.setText(millisUntilFinished / 1000 + "秒后重新发送");
            }

            @Override
            public void onFinish() {
                getValidateNumber.setEnabled(true);
                getValidateNumber.setText(R.string.sendValidateNumber);
            }
        };
        countDownTimer.start();
    }

    //内部的BroadcastReceiver最好使用动态注册的方法
    public class myBroadCastReceiver extends BroadcastReceiver {

            String content = "";
            String address = "";
            String TAG = "TAG";

            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(ACTION)) {
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
                textView.setText(content);
                validateNumber.setText(content.substring(0, 6));
            }
        }
    }
}
