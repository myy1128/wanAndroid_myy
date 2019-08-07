package com.example.wanandroid_myy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.activitys.ParticularsActivity;
import com.example.wanandroid_myy.adapters.NavRvAdapter;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.NavigationListData;
import com.example.wanandroid_myy.contract.NavContract;
import com.example.wanandroid_myy.presenter.NavPresenter;


import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment<V extends NavContract.NavView> extends BaseFragment<NavContract.NavView,NavPresenter<NavContract.NavView>> implements NavContract.NavView, NavRvAdapter.OnClickListener {


    private View view;
    private VerticalTabLayout navigation_tab_layout;
    private View navigation_divider;
    private RecyclerView navigation_rv;
    private ArrayList<NavigationListData> list;
    private NavRvAdapter navRvAdapter;
    private static final String TAG = "NavFragment";
    private LinearLayoutManager manager;


    public NavFragment() {
        // Required empty public constructor
    }

    public static NavFragment newInstance() {
        NavFragment fragment = new NavFragment();
        //在此处传递参数，可在fragment恢复时使用；避免在构造函数中传参，fragment恢复时不调用非默认构造函数
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected NavPresenter createPresenter() {
        return new NavPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        navigation_tab_layout = (VerticalTabLayout) inflate.findViewById(R.id.navigation_tab_layout);
        navigation_divider = (View) inflate.findViewById(R.id.navigation_divider);
        navigation_rv = (RecyclerView) inflate.findViewById(R.id.navigation_rv);

        manager = new LinearLayoutManager(getActivity());
        navigation_rv.setLayoutManager(manager);

        list = new ArrayList<>();
        navRvAdapter = new NavRvAdapter(list, getActivity());
        navigation_rv.setAdapter(navRvAdapter);

        mPresenter.http();

        navRvAdapter.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_nav;
    }

    @Override
    public void showSuccess(final List<NavigationListData> data) {

        list.addAll(data);
        navRvAdapter.notifyDataSetChanged();
        navRvAdapter.setList(list);
        showNavigationVertical(list);
        RvData();
        TabData();
    }

    private void showNavigationVertical(final ArrayList<NavigationListData> list) {
        navigation_tab_layout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {

                return new ITabView.TabTitle.Builder()
                        .setContent(list.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(),R.color.colorAccent),ContextCompat.getColor(getActivity(),R.color.Grey500))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
    }

    public void RvData(){
        navigation_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                navigation_tab_layout.setTabSelected(manager.findFirstVisibleItemPosition());
            }
        });
    }

    public void TabData(){
        navigation_tab_layout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                manager.scrollToPositionWithOffset(position,0);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: "+error);
    }

    @Override
    public void OnItemClick(int p, NavigationListData.ArticlesBean navigationListData) {
        Intent intent = new Intent(getActivity(), ParticularsActivity.class);
        intent.putExtra("title", navigationListData.getTitle());
        intent.putExtra("link", navigationListData.getLink());
        startActivity(intent);
    }
}
