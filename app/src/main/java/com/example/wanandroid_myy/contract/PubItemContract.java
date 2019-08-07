package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.PubItemBean;

public interface PubItemContract {
    interface PubItemView{
        void showSuccess(PubItemBean data);
        void showError(String error);
    }
    interface PubItemPresenter{
        void http(int cid,int page);
    }
    interface PubItemModel{
        interface CallBack{
            void showSuccess(PubItemBean data);
            void showError(String error);
        }
        void getData(int cid,int page, CallBack callBack);
    }
}
