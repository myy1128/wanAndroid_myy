package com.example.wanandroid_myy.presenter;

import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.contract.KnowledgeContract;
import com.example.wanandroid_myy.contract.ProTitleContract;
import com.example.wanandroid_myy.model.KnowledgeModel;
import com.example.wanandroid_myy.model.ProTitleModel;

import java.util.List;

public class ProTitlePresenter<V extends ProTitleContract.ProTitleView> extends BasePresenter<V> implements ProTitleContract.ProTitlePresenter, ProTitleContract.ProTitleModel.CallBack {
    private ProTitleContract.ProTitleModel proTitleModel = new ProTitleModel();

    @Override
    public void http() {
        if (mView != null) {
            proTitleModel.getData(this);
        }
    }

    @Override
    public void showSuccess(List<ProTitleBean> data) {
        mView.showSuccess(data);
    }

    @Override
    public void showError(String error) {
        mView.showError(error);
    }
}
