package com.example.wanandroid_myy.model;



import android.util.Log;

import com.example.wanandroid_myy.base.BaseObserver;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.FriendBean;
import com.example.wanandroid_myy.bean.PubTitleBean;
import com.example.wanandroid_myy.contract.FriendContract;
import com.example.wanandroid_myy.contract.PubTitleContract;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import java.util.List;

public class FriendModel implements FriendContract.FriendModel {

    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .getFriend()
                .compose(RxUtils.<BaseResponse<List<FriendBean>>>rxScheduleThread())
                .compose(RxUtils.<List<FriendBean>>changeResult())
                .subscribe(new BaseObserver<List<FriendBean>>() {
                    @Override
                    public void onSuccess(List<FriendBean> data) {
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
