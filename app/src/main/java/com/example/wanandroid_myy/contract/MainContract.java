package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.bean.ListData;

import java.util.List;

public interface MainContract {
    interface MainView{
        void showSuccess(ArticleListData data);
        void showBanSuccess(List<HomeBanBean> data);
        void showError(String error);
    }
    interface MainPresenter{
        void http(int num);
        void banhttp();
    }
    interface MainModel{
        interface CallBack{
            void showSuccess(ArticleListData data);
            void showBanSuccess(List<HomeBanBean> data);
            void showError(String error);
        }
        void getData(int num,CallBack callBack);
        void getbanData(CallBack callBack);
    }
}
