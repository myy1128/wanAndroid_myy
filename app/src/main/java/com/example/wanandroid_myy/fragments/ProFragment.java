package com.example.wanandroid_myy.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.adapters.VpTitleAdapter;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.presenter.ProTitlePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProFragment<V extends ProTitleContract.ProTitleView> extends BaseFragment<ProTitleContract.ProTitleView, ProTitlePresenter<ProTitleContract.ProTitleView>> implements ProTitleContract.ProTitleView {


    private View view;
    private TabLayout pro_tab;
    private ViewPager pro_vp;
    private ArrayList<Fragment> fs;
    private ArrayList<String> title;
    private static final String TAG = "ProFragment";

    public ProFragment() {
        // Required empty public constructor
    }

    public static ProFragment newInstance() {
        ProFragment fragment = new ProFragment();
        //在此处传递参数，可在fragment恢复时使用；避免在构造函数中传参，fragment恢复时不调用非默认构造函数
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected ProTitlePresenter createPresenter() {
        return new ProTitlePresenter();
    }

    @Override
    protected void initView(View inflate) {

        super.initView(inflate);

        pro_tab = (TabLayout) inflate.findViewById(R.id.pro_tab);
        pro_vp = (ViewPager) inflate.findViewById(R.id.pro_vp);

        mPresenter.http();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pro;
    }

    @Override
    public void showSuccess(List<ProTitleBean> data) {

        fs = new ArrayList<>();
        title = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            title.add(data.get(i).getName());
            ProItemFragment proItemFragment = new ProItemFragment();
            Bundle bundle = new Bundle();
            int id = data.get(i).getId();
            bundle.putInt("cid",id);
            proItemFragment.setArguments(bundle);
            fs.add(proItemFragment);
        }

        VpTitleAdapter vpTitleAdapter = new VpTitleAdapter(getChildFragmentManager(), fs,title);
        pro_vp.setAdapter(vpTitleAdapter);
        pro_tab.setupWithViewPager(pro_vp);

    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: " + error);
    }
}
