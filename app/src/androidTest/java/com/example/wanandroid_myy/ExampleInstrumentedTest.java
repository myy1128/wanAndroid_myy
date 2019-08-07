package com.example.wanandroid_myy;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.ListData;
import com.example.wanandroid_myy.http.HttpManager;
import com.example.wanandroid_myy.util.MyServer;
import com.example.wanandroid_myy.util.RxUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.wanandroid_myy", appContext.getPackageName());
    }
    @Test
    public void http(){
        HttpManager.getInstance().getServer(MyServer.class).get("wxarticle/chapters/json")
                .compose(RxUtils.<BaseResponse<List<ListData>>>rxScheduleThread())
                .compose(RxUtils.<List<ListData>>changeResult())
                .subscribe(new Consumer<List<ListData>>() {
                    @Override
                    public void accept(List<ListData> listData) throws Exception {
                        Log.e("mayiyan", "accept: "+listData.toString() );
                    }
                });
    }
}
