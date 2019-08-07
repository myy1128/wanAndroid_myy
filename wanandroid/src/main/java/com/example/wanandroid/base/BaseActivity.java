package com.example.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseActivity<V,P extends BasePersenter<V>> extends SimpleActivity {
    private P mPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPersenter = createpersneter();
        super.onCreate(savedInstanceState);
        if (mPersenter!=null){
            mPersenter.actach((V) this);
        }

    }

    protected abstract P createpersneter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPersenter!=null){
            mPersenter.detach();
        }

    }
}
