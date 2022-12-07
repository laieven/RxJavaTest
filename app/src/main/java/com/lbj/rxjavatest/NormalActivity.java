package com.lbj.rxjavatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NormalActivity extends Activity implements View.OnClickListener {
    private TextView mTextView;
    private Button mBtn;
    private TextView mTextViewEdit;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.text1);
        mTextViewEdit= (TextView) findViewById(R.id.edit1);
        mBtn= (Button) findViewById(R.id.button);


        mTextView.setOnClickListener(this);
        mBtn.setOnClickListener(this);
        mTextViewEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text1:
                break;
            case R.id.edit1:
                break;
            case R.id.button:
                if(mTextView.getText().toString()!=null ||mTextView.getText().toString().length()>0){
                    mTextView.setText("");
                }
                start();
                break;
        }
    }

    private void start() {
        Observable observable = createObservable();
        Observer observer = createObserver();

        mTextView.append("开始订阅，准备观察...\n");
        //进行订阅
        observable.subscribe(observer);
    }

    private Observer createObserver() {
        //创建观察者
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                //mDisposable可以随时取消订阅
                mDisposable = d;
            }

            @Override
            public void onNext(Object o) {
                String s = (String) o;
                Log.i("TAG", "onNext: " + s);
//                if (s.equals("33333")){
//                    mDisposable.dispose();
//                }
                mTextView.append(s + "\n");
                mTextView.append("执行观察者中的onNext()...\n");

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError: " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                mTextView.append("执行观察者中的onCompleted()...\n");
                mTextView.append("订阅完毕，结束观察...\n");
            }
        };
        return observer;
    }

    private Observable createObservable() {
        //创建被观察者
        Observable observable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("11111");
            emitter.onNext("22222");
            emitter.onNext("33333");
            emitter.onComplete();
        });
        return observable;
    }
}