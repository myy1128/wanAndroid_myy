package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.contract.MainContract;
import com.example.wanandroid_myy.model.HomeModel;

import java.util.List;

public class HomePresenter<V extends MainContract.MainView> extends BasePresenter<V> implements MainContract.MainPresenter, MainContract.MainModel.CallBack {
    private MainContract.MainModel mainModel = new HomeModel();

    @Override
    public void http(int num) {
        if (mView != null) {
            mainModel.getData(num, this);
        }
    }

    @Override
    public void banhttp() {
        if (mView != null) {
            mainModel.getbanData(this);
        }
    }

    @Override
    public void showSuccess(ArticleListData data) {
        mView.showSuccess(data);
    }

    @Override
    public void showBanSuccess(List<HomeBanBean> data) {
        mView.showBanSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
