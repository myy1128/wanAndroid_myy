package com.example.wanandroid_myy.model;

import android.util.Log;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.bean.ListData;
import com.example.wanandroid_myy.contract.MainContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class HomeModel implements MainContract.MainModel {
    @Override
    public void getData(int num,final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getArticleList(num)
                .compose(RxUtils.<BaseResponse<ArticleListData>>rxScheduleThread())
                .compose(RxUtils.<ArticleListData>changeResult())
                .subscribe(new BaseObserver<ArticleListData>() {
                    @Override
                    public void onSuccess(ArticleListData data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }

    @Override
    public void getbanData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getBannerData()
                .compose(RxUtils.<BaseResponse<List<HomeBanBean>>>rxScheduleThread())
                .compose(RxUtils.<List<HomeBanBean>>changeResult())
                .subscribe(new BaseObserver<List<HomeBanBean>>() {
                    @Override
                    public void onSuccess(List<HomeBanBean> data) {
                        callBack.showBanSuccess(data);

                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);

                    }
                });
    }
}
