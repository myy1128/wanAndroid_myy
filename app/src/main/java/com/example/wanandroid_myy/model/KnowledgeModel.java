package com.example.wanandroid_myy.model;

import android.util.Log;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class KnowledgeModel implements KnowledgeContract.KnowledgeModel {

    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getKnowledgeTreeData()
                .compose(RxUtils.<BaseResponse<List<KnowledgeTreeData>>>rxScheduleThread())
                .compose(RxUtils.<List<KnowledgeTreeData>>changeResult())
                .subscribe(new BaseObserver<List<KnowledgeTreeData>>() {
                    @Override
                    public void onSuccess(List<KnowledgeTreeData> data) {
                        callBack.showSuccess(data);
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                    }
                });
    }
}
