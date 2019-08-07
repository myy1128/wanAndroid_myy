package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.ProItemContract;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.model.ProItemModel;
import com.example.wanandroid_myy.model.ProTitleModel;

import java.util.List;

public class ProItemPresenter<V extends ProItemContract.ProItemView> extends BasePresenter<V> implements ProItemContract.ProItemPresenter, ProItemContract.ProItemModel.CallBack {
    private ProItemContract.ProItemModel proItemModel = new ProItemModel();


    @Override
    public void http(int cid) {
        if (mView != null) {
            proItemModel.getData(cid, this);
        }
    }

    @Override
    public void showSuccess(ProItemBean data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
