package com.example.wanandroid_myy.model;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.KnowItemBean;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.contract.KnowItemContract;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class KnowItemModel implements KnowItemContract.KnowItemModel {

    @Override
    public void getData(int cid,final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getKnowItemData(0,cid)
                .compose(RxUtils.<BaseResponse<KnowItemBean>>rxScheduleThread())
                .compose(RxUtils.<KnowItemBean>changeResult())
                .subscribe(new BaseObserver<KnowItemBean>() {
                    @Override
                    public void onSuccess(KnowItemBean data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }
}
