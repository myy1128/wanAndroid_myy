package com.example.wanandroid_myy.http;

import android.util.Log;

import com.example.wanandroid_myy.app.Globle;
import com.example.wanandroid_myy.app.MyApp;
import com.example.wanandroid_myy.util.HttpUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Timeout;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpManager {
    private static volatile HttpManager httpManager;

    public HttpManager() {

    }
    public static HttpManager getInstance(){
        if (httpManager==null){
            synchronized (HttpManager.class){
                if (httpManager==null){
                    httpManager = new HttpManager();
                }
            }
        }
        return httpManager;
    }

    public <T> T getServer(Class<T> tClass){

        return getRetrofit().create(tClass);
    }

    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Globle.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkhttpClient())
                .build();
    }

    private OkHttpClient getOkhttpClient() {

        //日志过滤器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("OKHttp-----", message);
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //指定缓存的路径
        Cache cache = new Cache(new File(MyApp.getInstance().getCacheDir(), "Cache"), 1024 * 1024 * 10);


        MyCacheinterceptor myCacheinterceptor=new MyCacheinterceptor();
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                //失败自动重连
                .retryOnConnectionFailure(true)
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                //添加缓存拦截器
                .cache(cache)
                .addInterceptor(myCacheinterceptor)
                .addNetworkInterceptor(myCacheinterceptor)
                .build();
    }
    //post 不可以做缓存
    private class MyCacheinterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
            if (!HttpUtils.isNetworkAvailable(MyApp.getInstance())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }

            //利用拦截器发送出去
            Response originalResponse = chain.proceed(request);
            if(HttpUtils.isNetworkAvailable(MyApp.getInstance())) {
                int maxAge = 0;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            }else{
                int maxStale = 15;
                return originalResponse.newBuilder()
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    }
}
