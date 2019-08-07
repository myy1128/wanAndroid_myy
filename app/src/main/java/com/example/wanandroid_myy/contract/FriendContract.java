package com.example.wanandroid_myy.contract;

import com.example.wanandroid_myy.bean.FriendBean;
import com.example.wanandroid_myy.bean.PubTitleBean;

import java.util.List;

public interface FriendContract {
    interface FriendView{
        void showSuccess(List<FriendBean> data);
        void showError(String error);
    }
    interface FriendPresenter{
        void http();
    }
    interface FriendModel{
        interface CallBack{
            void showSuccess(List<FriendBean> data);
            void showError(String error);
        }
        void getData(CallBack callBack);
    }
}
