package com.example.wanandroid_myy.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.activitys.ParticularsActivity;
import com.example.wanandroid_myy.adapters.ProItemRvAdapter;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.contract.ProItemContract;
import com.example.wanandroid_myy.presenter.ProItemPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProItemFragment<V extends ProItemContract.ProItemView> extends BaseFragment<ProItemContract.ProItemView,ProItemPresenter<ProItemContract.ProItemView>> implements ProItemContract.ProItemView, ProItemRvAdapter.OnClickListener {


    private View view;
    private RecyclerView pro_rv;
    private ArrayList<ProItemBean.DatasBean> list;
    private ProItemRvAdapter proItemRvAdapter;
    private static final String TAG = "ProItemFragment";

    public ProItemFragment() {
        // Required empty public constructor
    }

    @Override
    protected ProItemPresenter createPresenter() {
        return new ProItemPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        pro_rv = (RecyclerView) inflate.findViewById(R.id.pro_rv);
        pro_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        proItemRvAdapter = new ProItemRvAdapter(list, getActivity());
        pro_rv.setAdapter(proItemRvAdapter);

        int cid = getArguments().getInt("cid");

        mPresenter.http(cid);

        proItemRvAdapter.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pro_item;
    }

    @Override
    public void showSuccess(ProItemBean data) {
        list.addAll(data.getDatas());
        proItemRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: "+error);
    }

    @Override
    public void OnItemClick(int p, ProItemBean.DatasBean datasBean) {
        Intent intent = new Intent(getActivity(), ParticularsActivity.class);
        intent.putExtra("title", datasBean.getTitle());
        intent.putExtra("link", datasBean.getLink());
        startActivity(intent);
    }
}
