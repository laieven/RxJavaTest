package com.lbj.rxjavatest.function;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.lbj.rxjavatest.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * 将 Person 集合中的每个元素中的 Plan 的 action 打印出来
 */
public class FlatMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_map);


        List<Plan> planList1 = new ArrayList<>();
        Plan plan1 = new Plan("time1", "content1");
        planList1.add(plan1);
        ArrayList<String> actionList1 = new ArrayList<>();
        actionList1.add("1");
        actionList1.add("11");
        actionList1.add("1111");
        plan1.setActionList(actionList1);
        Person person1 = new Person("1", planList1);


        List<Plan> planList2 = new ArrayList<>();
        Plan plan2 = new Plan("time2", "content2");
        planList2.add(plan2);
        ArrayList<String> actionList2 = new ArrayList<>();
        actionList2.add("2");
        actionList2.add("22");
        actionList2.add("2222");
        plan2.setActionList(actionList2);
        Person person2 = new Person("2", planList2);


        List<Plan> planList3 = new ArrayList<>();
        Plan plan3 = new Plan("time3", "content3");
        planList3.add(plan3);
        ArrayList<String> actionList3 = new ArrayList<>();
        actionList3.add("3");
        actionList3.add("33");
        actionList3.add("3333");
        plan3.setActionList(actionList3);
        Person person3 = new Person("3", planList3);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);

//        Observable
//                .fromIterable(personList)
//                .map(person -> person.getPlanList())
//                .subscribe(new Observer<List<Plan>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Plan> plans) {
//                        for (Plan plan : plans){
//                            List<String> actionList = plan.getActionList();
//                            for (String action : actionList){
//                                Log.i("TAG", "action is: " + action);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        //使用flatMap
        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    //person中去处planList
                    @Override
                    public ObservableSource<Plan> apply(Person person) throws Exception {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .flatMap(new Function < Plan, ObservableSource < String >> () {
                    //planList得到具体的plan
                    @Override
                    public ObservableSource < String > apply(Plan plan) throws Exception {
                        return Observable.fromIterable(plan.getActionList());
                    }
                })
                .subscribe(new Observer < String > () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d("TAG", "==================action: " + s);
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