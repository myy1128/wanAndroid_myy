package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.ProTitleBean;

import java.util.List;

public interface ProItemContract {
    interface ProItemView{
        void showSuccess(ProItemBean data);
        void showError(String error);
    }
    interface ProItemPresenter{
        void http(int cid);
    }
    interface ProItemModel{
        interface CallBack{
            void showSuccess(ProItemBean data);
            void showError(String error);
        }
        void getData(int cid,CallBack callBack);
    }
}
