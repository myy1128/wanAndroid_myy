package com.example.wanandroid_myy.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.activitys.ParticularsActivity;
import com.example.wanandroid_myy.adapters.HomeRvAdapter;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.contract.MainContract;
import com.example.wanandroid_myy.presenter.HomePresenter;
import com.example.wanandroid_myy.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment<V extends MainContract.MainView> extends BaseFragment<MainContract.MainView, HomePresenter<MainContract.MainView>> implements MainContract.MainView,  HomeRvAdapter.MyOnClickListener {

    private View view;
    private RecyclerView home_rv;
    private ArrayList<ArticleListData.DatasBean> list;
    private HomeRvAdapter homeRvAdapter;
    private static final String TAG = "HomeFragment";
    private ArrayList<HomeBanBean> banList;
    private SmartRefreshLayout smart_refresh_layout;
    private int page = 1;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        //在此处传递参数，可在fragment恢复时使用；避免在构造函数中传参，fragment恢复时不调用非默认构造函数
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(final View inflate) {
        home_rv = (RecyclerView) inflate.findViewById(R.id.home_rv);
        smart_refresh_layout = (SmartRefreshLayout) inflate.findViewById(R.id.smart_refresh_layout);
        home_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        banList = new ArrayList<>();
        homeRvAdapter = new HomeRvAdapter(list, banList, getActivity());
        home_rv.setAdapter(homeRvAdapter);

        initBanData();
        initItemData();

        homeRvAdapter.setMyOnClickListener(this);

        smart_refresh_layout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initItemData();
                smart_refresh_layout.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                list.clear();
                initItemData();
                homeRvAdapter.notifyDataSetChanged();
                smart_refresh_layout.finishRefresh();
            }
        });
    }

    public void initBanData() {
        mPresenter.banhttp();
    }

    public void initItemData() {
        mPresenter.http(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showSuccess(ArticleListData data) {
        list.addAll(data.getDatas());
        homeRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBanSuccess(List<HomeBanBean> data) {
        banList.addAll(data);
        homeRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: " + error);
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void OnItemClick(int p, ArticleListData.DatasBean datasBean) {
        Intent intent = new Intent(getActivity(), ParticularsActivity.class);
        intent.putExtra("title", datasBean.getTitle());
        intent.putExtra("link", datasBean.getLink());
        startActivity(intent);
    }
}
