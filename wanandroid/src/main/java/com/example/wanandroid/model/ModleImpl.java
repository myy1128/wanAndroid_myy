package com.example.wanandroid.model;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HomeListBean;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.http.HttpManager;
import com.example.wanandroid.http.MyServer;
import com.example.wanandroid.utlis.RxUtils;

import java.util.List;


import io.reactivex.functions.Consumer;

public class ModleImpl implements MainContract.MainModle {
    @Override
    public void getData(final CallBack callBack) {
        HttpManager.getInstance().getServer(MyServer.class)
                .gethomelist("article/list/1/json")
                .compose(RxUtils.<BaseResponse<HomeListBean>>rxScheduleThread())
                .compose(RxUtils.<HomeListBean>changeResult())
                .subscribe(new Consumer<HomeListBean>() {
                    @Override
                    public void accept(HomeListBean homeListBean) throws Exception {
                        callBack.showSuccess(homeListBean);
                    }
                });
    }

    public void getbannerList(final CallBack callback){
        HttpManager.getInstance().getServer(MyServer.class)
                .getBannerlist("banner/json")
                .compose(RxUtils.<BaseResponse<List<BannerBean.DataBean>>>rxScheduleThread())
                .compose(RxUtils.<List<BannerBean.DataBean>>changeResult())
                .subscribe(new Consumer<List<BannerBean.DataBean>>() {
                    @Override
                    public void accept(List<BannerBean.DataBean> dataBeans) throws Exception {
                        callback.showSuccess(dataBeans);
                    }
                });
    }

}
