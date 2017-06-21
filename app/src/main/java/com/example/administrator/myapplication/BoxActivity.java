package com.example.administrator.myapplication;

/*
 * Created by Administrator on 2017/03/30.
 */

import android.app.Activity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class BoxActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box);

        final RadioGroup rg1 = (RadioGroup) findViewById(R.id.box_rg1);
        final RadioGroup rg2 = (RadioGroup) findViewById(R.id.box_rg2);
        final CheckBox cb1 = (CheckBox) findViewById(R.id.box_cb1);
        final CheckBox cb2 = (CheckBox) findViewById(R.id.box_cb2);
        final CheckBox cb3 = (CheckBox) findViewById(R.id.box_cb3);
        final CheckBox cb4 = (CheckBox) findViewById(R.id.box_cb4);
        final CheckBox cb5 = (CheckBox) findViewById(R.id.box_cb5);
        final CheckBox cb6 = (CheckBox) findViewById(R.id.box_cb6);
        final TextView tv4 = (TextView) findViewById(R.id.box_tv4);
        final TextView tv5 = (TextView) findViewById(R.id.box_tv5);
        final TextView tv6 = (TextView) findViewById(R.id.box_tv6);
        final String textview1 = tv4.getText().toString();
        final String textview2 = tv5.getText().toString();

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton CheckedId = (RadioButton) findViewById(rg1.getCheckedRadioButtonId());
                tv4.setText(textview1 + CheckedId.getText().toString());
            }
        });
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton CheckedId = (RadioButton) findViewById(rg2.getCheckedRadioButtonId());
                tv5.setText(textview2 + CheckedId.getText().toString());
            }
        });

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String string = "";
                if (cb1.isChecked()) {
                    string += cb1.getText().toString();
                    string += " ";
                }
                if (cb2.isChecked()) {
                    string += cb2.getText().toString();
                    string += " ";
                }
                if (cb3.isChecked()) {
                    string += cb3.getText().toString();
                    string += " ";
                }
                if (cb4.isChecked()) {
                    string += cb4.getText().toString();
                    string += " ";
                }
                if (cb5.isChecked()) {
                    string += cb5.getText().toString();
                    string += " ";
                }

                tv6.setText("你最近看过的书有:" + string);

                if (buttonView.getId() == R.id.box_cb6) {
                    if (cb6.isChecked()) {
                        cb1.setChecked(true);
                        cb2.setChecked(true);
                        cb3.setChecked(true);
                        cb4.setChecked(true);
                        cb5.setChecked(true);
                    } else {
                        if(cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked()){
                            cb1.setChecked(false);
                            cb2.setChecked(false);
                            cb3.setChecked(false);
                            cb4.setChecked(false);
                            cb5.setChecked(false);
                        }
                    }
                } else {
                    if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked()) {
                        cb6.setChecked(true);
                    }else{
                        cb6.setChecked(false);
                    }
                }


//                if (buttonView.isChecked()) {
//                    String textview3 = tv6.getText().toString();
//                    tv6.setText(textview3 + "  " + buttonView.getText().toString());
//                    checkAll();
//                }
//                if (!buttonView.isChecked()) {
//                    String textview = tv6.getText().toString();
//                    //Toast.makeText(BoxActivity.this,buttonView.getText().toString(),Toast.LENGTH_SHORT).show(); 小窗口短时间显示取消选择的项目
//                    tv6.setText(textview.replace(buttonView.getText().toString(), ""));
//                    clearAll();
//                }
            }

//            private void clearAll() {
//                if (!cb1.isChecked() || !cb2.isChecked() || !cb3.isChecked() || !cb4.isChecked() || !cb5.isChecked()) {
//                    cb6.setChecked(false);
//                }
//            }
//
//            private void checkAll() {
//                if (cb1.isChecked() && cb2.isChecked() && cb3.isChecked() && cb4.isChecked() && cb5.isChecked()) {
//                    cb6.setChecked(true);
//                }
//            }
        };
        //新建一个监听器之后，多个控件调用这个监听器，避免了代码的冗余
        cb1.setOnCheckedChangeListener(listener);
        cb2.setOnCheckedChangeListener(listener);
        cb3.setOnCheckedChangeListener(listener);
        cb4.setOnCheckedChangeListener(listener);
        cb5.setOnCheckedChangeListener(listener);
        cb6.setOnCheckedChangeListener(listener);
    }

}
