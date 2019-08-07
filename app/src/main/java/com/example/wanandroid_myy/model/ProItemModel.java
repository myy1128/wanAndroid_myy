package com.example.wanandroid_myy.model;

import android.util.Log;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.ProItemContract;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class ProItemModel implements ProItemContract.ProItemModel {

    @Override
    public void getData(int cid,final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getProItemData(1,cid)
                .compose(RxUtils.<BaseResponse<ProItemBean>>rxScheduleThread())
                .compose(RxUtils.<ProItemBean>changeResult())
                .subscribe(new BaseObserver<ProItemBean>() {
                    @Override
                    public void onSuccess(ProItemBean data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }
}
