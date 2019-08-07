package com.example.wanandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid.R;
import com.example.wanandroid.adapers.HomeAdaper;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.HomeListBean;
import com.example.wanandroid.bean.ListData;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.persenter.Persenterimpl;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePagerFragment extends BaseFragment<HomePagerFragment, Persenterimpl> implements MainContract.MainView<HomeListBean>{


    @BindView(R.id.home_recyc)
    RecyclerView homeRecyc;
    @BindView(R.id.home_sma)
    SmartRefreshLayout homeSma;
    Unbinder unbinder;
    private HomeAdaper adaper;


    @Override
    protected void initView() {

        homeRecyc.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*
        * setHasFixedSize 的作用就是确保尺寸是通过用户输入从而确保RecyclerView的尺寸是一个常数。
        * RecyclerView 的Item宽或者高不会变。每一个Item添加或者删除都不会变。
        * 如果你没有设置setHasFixedSized没有设置的代价将会是非常昂贵的。因为RecyclerView会需要而外计算每个item的size，
        * */
        homeRecyc.setHasFixedSize(true);

        adaper = new HomeAdaper(getActivity());
        homeRecyc.setAdapter(adaper);

        mPersenter.http();


    }

    @Override
    protected int getlayout() {
        return R.layout.fragment_home_pager;
    }


    @Override
    public void showSuccesses(HomeListBean homeListBean) {
        List<HomeListBean.DatasBean> datas = homeListBean.getDatas();
        adaper.getlistdata(datas);
    }

    @Override
    public void showError(String error) {

    }
    public void getBannerlists(List<BannerBean.DataBean> bannerlist) {
        adaper.getbannerList(bannerlist);
    }



    @Override
    protected Persenterimpl createPersenter() {

        return new Persenterimpl();
    }
}
