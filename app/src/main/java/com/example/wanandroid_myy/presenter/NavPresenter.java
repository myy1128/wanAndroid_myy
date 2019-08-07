package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.NavigationListData;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.NavContract;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.model.NavModel;
import com.example.wanandroid_myy.model.ProTitleModel;

import java.util.List;

public class NavPresenter<V extends NavContract.NavView> extends BasePresenter<V> implements NavContract.NavPresenter, NavContract.NavModel.CallBack {
    private NavContract.NavModel navModel = new NavModel();

    @Override
    public void http() {
        if (mView != null) {
            navModel.getData(this);
        }
    }

    @Override
    public void showSuccess(List<NavigationListData> data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
