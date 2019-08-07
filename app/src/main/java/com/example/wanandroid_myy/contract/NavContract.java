package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.NavigationListData;
import com.example.wanandroid_myy.bean.ProTitleBean;

import java.util.List;

public interface NavContract {
    interface NavView{
        void showSuccess(List<NavigationListData> data);
        void showError(String error);
    }
    interface NavPresenter{
        void http();
    }
    interface NavModel{
        interface CallBack{
            void showSuccess(List<NavigationListData> data);
            void showError(String error);
        }
        void getData(CallBack callBack);
    }
}
