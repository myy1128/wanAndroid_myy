package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.bean.PubTitleBean;

import java.util.List;

public interface PubTitleContract {
    interface PubTitleView{
        void showSuccess(List<PubTitleBean> data);
        void showError(String error);
    }
    interface PubTitlePresenter{
        void http();
    }
    interface PubTitleModel{
        interface CallBack{
            void showSuccess(List<PubTitleBean> data);
            void showError(String error);
        }
        void getData(CallBack callBack);
    }
}
