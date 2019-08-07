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
import com.example.wanandroid_myy.adapters.PubItemRvAdapter;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.PubItemBean;
import com.example.wanandroid_myy.contract.PubItemContract;
import com.example.wanandroid_myy.presenter.PubItemPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PubItemFragment<V extends PubItemContract.PubItemView> extends BaseFragment<PubItemContract.PubItemView,PubItemPresenter<PubItemContract.PubItemView>> implements PubItemContract.PubItemView, PubItemRvAdapter.OnClickListener {


    private View view;
    private RecyclerView pub_rv;
    private ArrayList<PubItemBean.DatasBean> list;
    private PubItemRvAdapter pubItemRvAdapter;
    private static final String TAG = "PubItemFragment";

    public PubItemFragment() {
        // Required empty public constructor
    }

    @Override
    protected PubItemPresenter createPresenter() {
        return new PubItemPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        pub_rv = (RecyclerView) inflate.findViewById(R.id.pub_rv);
        pub_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        pubItemRvAdapter = new PubItemRvAdapter(list, getActivity());
        pub_rv.setAdapter(pubItemRvAdapter);

        int id = getArguments().getInt("id");
        int courseid = getArguments().getInt("courseid");

        mPresenter.http(id,courseid);

        pubItemRvAdapter.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pub_item;
    }

    @Override
    public void showSuccess(PubItemBean data) {
        list.addAll(data.getDatas());
        pubItemRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: "+error);
    }

    @Override
    public void OnItemClick(int p, PubItemBean.DatasBean datasBean) {
        Intent intent = new Intent(getActivity(), ParticularsActivity.class);
        intent.putExtra("title", datasBean.getTitle());
        intent.putExtra("link", datasBean.getLink());
        startActivity(intent);
    }
}
