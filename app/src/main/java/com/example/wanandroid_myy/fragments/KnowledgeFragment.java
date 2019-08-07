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
import com.example.wanandroid_myy.activitys.KnowItemActivity;
import com.example.wanandroid_myy.adapters.KnowledgeRvAdapter;
import com.example.wanandroid_myy.base.BaseFragment;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.presenter.KnowledgePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment<V extends KnowledgeContract.KnowledgeView> extends BaseFragment<KnowledgeContract.KnowledgeView,KnowledgePresenter<KnowledgeContract.KnowledgeView>> implements KnowledgeContract.KnowledgeView, KnowledgeRvAdapter.OnClickListener {

    private View view;
    private RecyclerView know_rv;
    private ArrayList<KnowledgeTreeData> list;
    private KnowledgeRvAdapter knowledgeRvAdapter;
    private static final String TAG = "KnowledgeFragment";

    public KnowledgeFragment() {
        // Required empty public constructor
    }

    public static KnowledgeFragment newInstance() {
        KnowledgeFragment fragment = new KnowledgeFragment();
        //在此处传递参数，可在fragment恢复时使用；避免在构造函数中传参，fragment恢复时不调用非默认构造函数
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View inflate) {
        super.initView(inflate);
        know_rv = (RecyclerView) inflate.findViewById(R.id.know_rv);
        know_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        knowledgeRvAdapter = new KnowledgeRvAdapter(list, getActivity());
        know_rv.setAdapter(knowledgeRvAdapter);
        mPresenter.http();

        knowledgeRvAdapter.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void showSuccess(List<KnowledgeTreeData> data) {
        list.addAll(data);
        knowledgeRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: "+error);
    }

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public void OnItemClick(int p, KnowledgeTreeData knowledgeTreeData) {
        Intent intent = new Intent(getActivity(), KnowItemActivity.class);
        intent.putExtra("data",knowledgeTreeData);
        startActivity(intent);
    }
}
