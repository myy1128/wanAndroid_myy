package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.bean.PubTitleBean;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.contract.PubTitleContract;
import com.example.wanandroid_myy.model.ProTitleModel;
import com.example.wanandroid_myy.model.PubTitleModel;

import java.util.List;

public class PubTitlePresenter<V extends PubTitleContract.PubTitleView> extends BasePresenter<V> implements PubTitleContract.PubTitlePresenter, PubTitleContract.PubTitleModel.CallBack {
    private PubTitleContract.PubTitleModel pubTitleModel = new PubTitleModel();

    @Override
    public void http() {
        if (mView != null) {
            pubTitleModel.getData(this);
        }
    }

    @Override
    public void showSuccess(List<PubTitleBean> data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
