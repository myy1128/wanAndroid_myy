package com.example.wanandroid_myy.model;

import android.util.Log;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.PubItemBean;
import com.example.wanandroid_myy.contract.ProItemContract;
import com.example.wanandroid_myy.contract.PubItemContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

public class PubItemModel implements PubItemContract.PubItemModel {

    @Override
    public void getData(int cid,int page,final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getPubItemData(cid,page)
                .compose(RxUtils.<BaseResponse<PubItemBean>>rxScheduleThread())
                .compose(RxUtils.<PubItemBean>changeResult())
                .subscribe(new BaseObserver<PubItemBean>() {
                    @Override
                    public void onSuccess(PubItemBean data) {
                        callBack.showSuccess(data);
                        Log.e("解析成功", "onSuccess: "+data.toString());
                    }

                    @Override
                    public void error(String error) {
                        callBack.showError(error);
                        Log.e("解析失败", "onSuccess: "+error);
                    }
                });
    }
}
