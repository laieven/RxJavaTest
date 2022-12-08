package com.lbj.rxjavatest.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lbj.rxjavatest.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class IntervalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);

        Observable.interval(10,4, TimeUnit.SECONDS)
                .subscribe(new Observer< Long >() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "==============onSubscribe ");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d("TAG", "==============onNext " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}