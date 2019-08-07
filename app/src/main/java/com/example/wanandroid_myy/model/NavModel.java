package com.example.wanandroid_myy.model;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.NavigationListData;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.NavContract;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class NavModel implements NavContract.NavModel {

    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getNavigationListData()
                .compose(RxUtils.<BaseResponse<List<NavigationListData>>>rxScheduleThread())
                .compose(RxUtils.<List<NavigationListData>>changeResult())
                .subscribe(new BaseObserver<List<NavigationListData>>() {
                    @Override
                    public void onSuccess(List<NavigationListData> data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }
}
