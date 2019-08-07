package com.example.wanandroid.persenter;

import android.util.Log;

import com.example.wanandroid.base.BasePersenter;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.HomeListBean;
import com.example.wanandroid.bean.ListData;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.fragments.HomePagerFragment;
import com.example.wanandroid.model.ModleImpl;

import java.util.List;

public class Persenterimpl extends BasePersenter<HomePagerFragment> implements MainContract.MainPresenter {
    private static final String TAG = "Persenterimpl";
    @Override
    public void http() {
        ModleImpl modle = new ModleImpl();
        modle.getData(new MainContract.MainModle.CallBack<HomeListBean>() {
            @Override
            public void showSuccess(HomeListBean datasBeans) {

                mView.showSuccesses(datasBeans);
            }

            @Override
            public void showError(String error) {
                Log.d(TAG, "showError: ================>>>>>>"+error);
            }
        });


        modle.getbannerList(new MainContract.MainModle.CallBack<List<BannerBean.DataBean>>() {
            @Override
            public void showSuccess(List<BannerBean.DataBean> dataBeans) {
                mView.getBannerlists(dataBeans);
            }

            @Override
            public void showError(String error) {
                Log.d(TAG, "banner: "+error);

            }
        });

    }



}
