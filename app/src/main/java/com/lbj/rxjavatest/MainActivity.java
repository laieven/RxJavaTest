package com.lbj.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lbj.rxjavatest.create.IntervalActivity;
import com.lbj.rxjavatest.function.FlatMapActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;
    private Button mBtnInterval;
    private Button mBtnFlatMap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button_interval).setOnClickListener(this);
        findViewById(R.id.button_flatMap).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                intent = new Intent(MainActivity.this, NormalActivity.class);
                break;
            case R.id.button2:
                intent=new Intent(MainActivity.this,RxMapActivity.class);
                break;
            case R.id.button_interval:
                intent = new Intent(MainActivity.this, IntervalActivity.class);
            case R.id.button_flatMap:
                intent = new Intent(MainActivity.this, FlatMapActivity.class);
        }
        startActivity(intent);
    }
}