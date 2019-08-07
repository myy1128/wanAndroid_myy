package com.example.wanandroid_myy.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class SimpleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);

//        EventBus.getDefault().register(this);
        initView(inflate);
        initData();
        return inflate;
    }

    protected void initData() {

    }

    protected void initView(View inflate) {

    }
    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
       /* if (bind!=null){
            bind.unbind();
        }*/
    }
}
