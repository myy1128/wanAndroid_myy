package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.PubItemBean;
import com.example.wanandroid_myy.contract.ProItemContract;
import com.example.wanandroid_myy.contract.PubItemContract;
import com.example.wanandroid_myy.model.ProItemModel;
import com.example.wanandroid_myy.model.PubItemModel;

public class PubItemPresenter<V extends PubItemContract.PubItemView> extends BasePresenter<V> implements PubItemContract.PubItemPresenter, PubItemContract.PubItemModel.CallBack {
    private PubItemContract.PubItemModel pubItemModel = new PubItemModel();


    @Override
    public void http(int cid,int page) {
        if (mView != null) {
            pubItemModel.getData(cid,page,this);
        }
    }

    @Override
    public void showSuccess(PubItemBean data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
