package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.FriendBean;
import com.example.wanandroid_myy.bean.PubTitleBean;
import com.example.wanandroid_myy.contract.FriendContract;
import com.example.wanandroid_myy.contract.PubTitleContract;
import com.example.wanandroid_myy.model.FriendModel;
import com.example.wanandroid_myy.model.PubTitleModel;

import java.util.List;

public class FriendPresenter<V extends FriendContract.FriendView> extends BasePresenter<V> implements FriendContract.FriendPresenter, FriendContract.FriendModel.CallBack {
    private FriendContract.FriendModel friendModel = new FriendModel();

    @Override
    public void http() {

            friendModel.getData(this);

    }

    @Override
    public void showSuccess(List<FriendBean> data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
