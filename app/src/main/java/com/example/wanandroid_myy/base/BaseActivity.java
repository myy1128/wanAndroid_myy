package com.example.wanandroid_myy.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends SimpleActivity {
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
