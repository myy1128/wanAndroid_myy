package com.example.wanandroid.http;




import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.BaseResponse;
import com.example.wanandroid.bean.HomeListBean;
import com.example.wanandroid.bean.ListData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * 项目名：MyMvpDemo
 * 包名：  com.liangxq.mydemo1.http
 * 文件名：MyServer
 * 创建者：liangxq
 * 创建时间：2019/7/24  20:46
 * 描述：TODO
 */
public interface MyServer {

    @GET
    Observable<BaseResponse<List<ListData>>> get(@Url String url);

    @GET
    Observable<BaseResponse<HomeListBean>> gethomelist(@Url String url);
    @GET
    Observable<BaseResponse<List<BannerBean.DataBean>>> getBannerlist(@Url String url);

}
