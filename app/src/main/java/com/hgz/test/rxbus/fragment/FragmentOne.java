package com.hgz.test.rxbus.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hgz.test.rxbus.R;
import com.hgz.test.rxbus.utils.RxBus;

/**
 * Created by Administrator on 2017/10/18.
 */

public class FragmentOne extends Fragment{

    private View view;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentone, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText = (EditText) view.findViewById(R.id.fragmentone_et);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RxBus.getInstance().post(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
