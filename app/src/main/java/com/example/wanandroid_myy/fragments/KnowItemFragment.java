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
import com.example.wanandroid_myy.adapters.KnowItemRvAdapter;
import com.example.wanandroid_myy.adapters.PubItemRvAdapter;
import com.example.wanandroid_myy.base.BaseActivity;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.KnowItemBean;
import com.example.wanandroid_myy.contract.KnowItemContract;
import com.example.wanandroid_myy.presenter.KnowItemPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowItemFragment<V extends KnowItemContract.KnowItemView> extends BaseFragment<KnowItemContract.KnowItemView, KnowItemPresenter<KnowItemContract.KnowItemView>> implements KnowItemContract.KnowItemView, KnowItemRvAdapter.OnClickListener {


    private View view;
    private RecyclerView know_item_rv;
    private ArrayList<KnowItemBean.DatasBean> list;
    private KnowItemRvAdapter knowItemRvAdapter;
    private static final String TAG = "KnowItemFragment";

    public KnowItemFragment() {
        // Required empty public constructor
    }


    @Override
    protected KnowItemPresenter createPresenter() {
        return new KnowItemPresenter();
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        know_item_rv = (RecyclerView) inflate.findViewById(R.id.know_item_rv);
        know_item_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        knowItemRvAdapter = new KnowItemRvAdapter(list, getActivity());
        know_item_rv.setAdapter(knowItemRvAdapter);

        int id = getArguments().getInt("id");

        mPresenter.http(id);

        knowItemRvAdapter.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_know_item;
    }

    @Override
    public void showSuccess(KnowItemBean data) {
        list.addAll(data.getDatas());
        knowItemRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: "+error);
    }

    @Override
    public void OnItemClick(int p, KnowItemBean.DatasBean datasBean) {
        Intent intent = new Intent(getActivity(), ParticularsActivity.class);
        intent.putExtra("title", datasBean.getTitle());
        intent.putExtra("link", datasBean.getLink());
        startActivity(intent);
    }
}
