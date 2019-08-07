package com.example.wanandroid.contract;

import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.bean.HomeListBean;
import com.example.wanandroid.bean.ListData;

import java.util.List;

/**
 * 项目名：MyMvpDemo
 * 包名：  com.liangxq.mydemo1.contract
 * 文件名：MainContract
 * 创建者：liangxq
 * 创建时间：2019/7/25  2:49
 * 描述：TODO
 */
public interface MainContract {

    interface MainView<T>{

        void showSuccesses(T t);

        void showError(String error);
    }


    interface MainPresenter{
        void http();
    }


    interface MainModle{
        interface CallBack<T>{
            void showSuccess(T t);

            void showError(String error);
        }
        void getData(CallBack callBack);
    }
}
