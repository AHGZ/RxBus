package com.hgz.test.rxbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgz.test.rxbus.R;
import com.hgz.test.rxbus.utils.RxBus;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/10/18.
 */

public class FragmentTwo extends Fragment{

    private View view;
    private TextView textView;
    private CompositeSubscription msubscription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmenttwo, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView) view.findViewById(R.id.fragmenttwo_text);
        Subscription subscription = RxBus.getInstance().toObservable(String.class)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String string) {
                        textView.setText(string);
                    }
                });
        addSubscription(subscription);
    }
    public void addSubscription(Subscription subscription){
        if (msubscription==null){
            msubscription=new CompositeSubscription();
        }
        msubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (msubscription!=null){
            msubscription.unsubscribe();
        }
    }
}
