package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.bean.ProTitleBean;

import java.util.List;

public interface ProTitleContract {
    interface ProTitleView{
        void showSuccess(List<ProTitleBean> data);
        void showError(String error);
    }
    interface ProTitlePresenter{
        void http();
    }
    interface ProTitleModel{
        interface CallBack{
            void showSuccess(List<ProTitleBean> data);
            void showError(String error);
        }
        void getData(CallBack callBack);
    }
}
