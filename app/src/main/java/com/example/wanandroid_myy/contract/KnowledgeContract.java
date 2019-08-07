package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;

import java.util.List;

public interface KnowledgeContract {
    interface KnowledgeView{
        void showSuccess(List<KnowledgeTreeData> data);
        void showError(String error);
    }
    interface KnowledgePresenter{
        void http();
    }
    interface KnowledgeModel{
        interface CallBack{
            void showSuccess(List<KnowledgeTreeData> data);
            void showError(String error);
        }
        void getData(KnowledgeContract.KnowledgeModel.CallBack callBack);
    }
}
