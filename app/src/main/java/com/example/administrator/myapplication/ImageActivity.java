package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.lang.reflect.Field;

/*
 * Created by Administrator on 2017/04/11.
 */

public class ImageActivity extends Activity {

    public static int order = 1;

    public int[] ID;

    public String[] imageID = new String[]{"pic1", "pic2", "pic3", "pic4", "pic5", "pic6", "pic7"};

    public ImageView imageView;

    public Class drawable = R.drawable.class;

    public Field field = null;

    public ArrayAdapter<String> adapter;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);

        final Button button1;
        Button button2;

        imageView = (ImageView) findViewById(R.id.imageview);
        button1 = (Button) findViewById(R.id.img_last);
        button2 = (Button) findViewById(R.id.img_next);

        spinner = (Spinner) findViewById(R.id.img_spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, imageID);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                showImageView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner.setAdapter(adapter);

        //Last
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order < 0) {
                    order = 6;
                    showImageView(order);
                    spinner.setSelection(order);
                    order--;
                } else {
                    showImageView(order);
                    spinner.setSelection(order);
                    order--;
                }
            }
        });
        //Next
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order > 6) {
                    order = 0;
                    showImageView(order);
                    spinner.setSelection(order);
                    order++;
                } else {
                    showImageView(order);
                    spinner.setSelection(order);
                    order++;
                }
            }
        });
    }

    private void showImageView(int order) {
        try {
            field = drawable.getField(imageID[order]);
            int PicId = field.getInt(field.getName());
            imageView.setBackgroundResource(PicId);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
