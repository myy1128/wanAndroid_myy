package com.example.wanandroid_myy.model;



import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.bean.PubTitleBean;
import com.example.wanandroid_myy.contract.PubTitleContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class PubTitleModel implements PubTitleContract.PubTitleModel {

    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getPubTitleData()
                .compose(RxUtils.<BaseResponse<List<PubTitleBean>>>rxScheduleThread())
                .compose(RxUtils.<List<PubTitleBean>>changeResult())
                .subscribe(new BaseObserver<List<PubTitleBean>>() {
                    @Override
                    public void onSuccess(List<PubTitleBean> data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }
}
