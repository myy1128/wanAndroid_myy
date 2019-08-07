package com.example.wanandroid.base;

import java.lang.ref.WeakReference;

public class BasePersenter<V>  {
     WeakReference<V> weakReference;
    public V mView;

    public void actach(V view){
        weakReference = new WeakReference<>(view);
        mView =weakReference.get();
    }


    public void detach(){
        if (weakReference!=null){
            weakReference.clear();
        }
    }

}
