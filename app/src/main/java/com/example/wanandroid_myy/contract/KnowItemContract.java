package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.KnowItemBean;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;

import java.util.List;

public interface KnowItemContract {
    interface KnowItemView{
        void showSuccess(KnowItemBean data);
        void showError(String error);
    }
    interface KnowItemPresenter{
        void http(int cid);
    }
    interface KnowItemModel{
        interface CallBack{
            void showSuccess(KnowItemBean data);
            void showError(String error);
        }
        void getData(int cid,CallBack callBack);
    }
}
