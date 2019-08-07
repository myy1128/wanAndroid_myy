package com.example.wanandroid_myy.model;

import android.util.Log;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class ProTitleModel implements ProTitleContract.ProTitleModel {

    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getProTitleData()
                .compose(RxUtils.<BaseResponse<List<ProTitleBean>>>rxScheduleThread())
                .compose(RxUtils.<List<ProTitleBean>>changeResult())
                .subscribe(new BaseObserver<List<ProTitleBean>>() {
                    @Override
                    public void onSuccess(List<ProTitleBean> data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }
}
