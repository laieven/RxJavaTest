package com.lbj.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

@SuppressWarnings("all")
public class RxMapActivity extends Activity implements View.OnClickListener {
    private TextView mText;
    private Button mBtn;
    private TextView mEdit;
    private Integer[] number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        number = new Integer[]{1,2,3,4,5,6};
        initView();
    }

    private void initView() {
        mText= (TextView) findViewById(R.id.text1);
        mEdit= (TextView) findViewById(R.id.edit1);
        mBtn= (Button) findViewById(R.id.button);

//        mBtn.setText("判断数组中的小于3的数");
        Log.i("TAG", "initView: " + mEdit);
        mEdit.setText("输入Integer(int)：1,2,3,4,5,6 \n"+"\n"+"输出：type:true/false \n");

        mBtn.setOnClickListener(this);
        mText.setOnClickListener(this);
        mEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text1:
                break;
            case R.id.edit1:
                break;
            case R.id.button:
                if(mText.getText().toString()!=null ||mText.getText().toString().length()>0){
                    mText.setText("");
                }
                start();
                break;
        }
    }

    private void start() {
        mText.append("\n 输入参数： 1,2,3,4,5,6 \n");
        Observable
                .fromArray(number)
                .map(new Function<Integer, Boolean>() {
                    @Override
                    public Boolean apply(Integer integer) throws Exception {
                        mText.append("\n\n map()  Integer--->Boolean");

                        return integer < 6;
                    }
                })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        mText.append("\n观察到输出结果：\n");
                        mText.append(aBoolean.toString());
                    }
                });
    }
}