package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.model.KnowledgeModel;

import java.util.List;

public class KnowledgePresenter<V extends KnowledgeContract.KnowledgeView> extends BasePresenter<V> implements KnowledgeContract.KnowledgePresenter, KnowledgeContract.KnowledgeModel.CallBack {
    private KnowledgeContract.KnowledgeModel knowledgeModel = new KnowledgeModel();

    @Override
    public void http() {
        if (mView != null) {
            knowledgeModel.getData(this);
        }
    }

    @Override
    public void showSuccess(List<KnowledgeTreeData> data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
