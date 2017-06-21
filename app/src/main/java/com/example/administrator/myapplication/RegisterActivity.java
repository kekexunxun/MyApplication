package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by Administrator on 2017/03/29.
 */

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv4 = (TextView) findViewById(R.id.tv4);
                TextView re_tv1 = (TextView) findViewById(R.id.re_tv1);
                TextView re_tv2 = (TextView) findViewById(R.id.re_tv2);
                TextView re_tv3 = (TextView) findViewById(R.id.re_tv3);
                EditText et1 = (EditText) findViewById(R.id.et1);
                EditText et2 = (EditText) findViewById(R.id.et2);
                EditText et3 = (EditText) findViewById(R.id.et3);
                String name = et1.getText().toString();
                String pwd = et2.getText().toString();
                String phone = et3.getText().toString();
                String match1 = "^[\\\\u4e00-\\\\u9fa5_a-zA-Z0-9-]{4,16}$";//昵称格式：4-16个字符，支持中英文、数字、减号或下划线
                String match2 = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$";//6-20 位，字母、数字、字符
                String match3 = "^1(3|4|5|7|8)\\d{9}$";
                Pattern p_name = Pattern.compile(match1);
                Pattern p_pwd = Pattern.compile(match2);
                Pattern p_phone = Pattern.compile(match3);
                Matcher m_name = p_name.matcher(name);
                Matcher m_pwd = p_pwd.matcher(pwd);
                Matcher m_phone = p_phone.matcher(phone);
                if (name.trim().length() == 0){
                    //et1.setError("账号不能为空!");
                    re_tv1.setText("账号不能为空");
                }else{
                    re_tv1.setText("");
                }
                /*
                    String txt = edInput.getText().toString();
                    Pattern p = Pattern.compile("[0-9]*");
                    Matcher m = p.matcher(txt);
                    if(m.matches() ){
                        Toast.makeText(Main.this,"输入的是数字", Toast.LENGTH_SHORT).show();
                    }
                    利用正则表达式去判定EditText的内容
                */
                /*
                    private int CheckSecurity(string pwd){
                         return Regex.Replace(pwd, "^(?:([a-z])|([A-Z])|([0-9])|(.)){6,}|(.)+$", "$1$2$3$4$5").Length;
                    }
                    利用正则表达式验证密码强度
                    密码字符包括：小写字母、大写字母、数字、符号等；
                    这个正则会得到五个捕获组，前四个捕获组会告诉我们这个字符串包含有多少种组合（返回多少个匹配代表多少种组合）
                    如果这个字符串小于6位的话,则会得到第五个捕获组,长度为1（即强度为1），如果没有输入，就连捕获组5都不会得到（强度为0）
                */
                if (pwd.trim().length() == 0){
                    //et2.setError("密码不能为空!");
                    re_tv2.setText("密码不能为空!");
                }else{
                    re_tv2.setText("");
                }
                if (phone.trim().length() == 0){
                    //et3.setError("电话不能为空!");
                    re_tv3.setText("电话不能为空!");
                }else {
                    re_tv3.setText("");
                }
                if (!m_name.matches()){
                    //et1.setError("字母、数字、下划线组成，字母开头，4-16位");
                    re_tv1.setText("账号错误：字母、数字、下划线组成，字母开头，4-16位");
                }else {
                    re_tv1.setText("");
                }
                if (!m_pwd.matches()){
                    //et2.setError("6-20 位，字母、数字、字符");
                    re_tv2.setText("密码错误：6-20 位，字母、数字、字符");
                }else {
                    re_tv2.setText("");
                }
                if (!m_phone.matches()){
                    //et3.setError("手机号码错误：11位数字，以1开头");
                    re_tv3.setText("手机号码错误：11位数字，以1开头");
                }else{
                    re_tv3.setText("");
                }
                tv4.setText(String.format("账号:%s\n密码:%s\n电话:%s", name,pwd,phone));
            }
        });

    }
}
