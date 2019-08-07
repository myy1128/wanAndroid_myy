package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.KnowItemBean;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.contract.KnowItemContract;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.model.KnowItemModel;
import com.example.wanandroid_myy.model.KnowledgeModel;

import java.util.List;

public class KnowItemPresenter<V extends KnowItemContract.KnowItemView> extends BasePresenter<V> implements KnowItemContract.KnowItemPresenter, KnowItemContract.KnowItemModel.CallBack {
    private KnowItemContract.KnowItemModel knowItemModel = new KnowItemModel();

    @Override
    public void http(int cid) {
        if (mView != null) {
            knowItemModel.getData(cid,this);
        }
    }

    @Override
    public void showSuccess(KnowItemBean data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
