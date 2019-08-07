package com.example.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.persenter.Persenterimpl;

public abstract class BaseFragment<V, P extends BasePersenter<V>> extends SimpleFragment {
    public P mPersenter;

    @Override
    protected void initpersenter() {
        mPersenter = createPersenter();
        if (mPersenter!=null){
            mPersenter.actach((V) this);
        }
    }

    protected abstract P createPersenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPersenter != null) {
            mPersenter.detach();
        }
    }
}
