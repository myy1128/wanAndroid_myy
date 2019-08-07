package com.example.wanandroid_myy.util;

import com.example.wanandroid_myy.bean.ArticleListData;
import com.example.wanandroid_myy.bean.BaseResponse;
import com.example.wanandroid_myy.bean.FriendBean;
import com.example.wanandroid_myy.bean.HomeBanBean;
import com.example.wanandroid_myy.bean.HomeTopBean;
import com.example.wanandroid_myy.bean.KnowItemBean;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.bean.NavigationListData;
import com.example.wanandroid_myy.bean.ProItemBean;
import com.example.wanandroid_myy.bean.ProTitleBean;
import com.example.wanandroid_myy.bean.PubItemBean;
import com.example.wanandroid_myy.bean.PubTitleBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyServer {

    /**
     * 广告栏
     * https://www.wanandroid.com/banner/json
     *
     * @return 广告栏数据
     */
    @GET("banner/json")
    Observable<BaseResponse<List<HomeBanBean>>> getBannerData();

    /**
     * 获取首页置顶文章列表
     * https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    Observable<BaseResponse<List<HomeTopBean>>> getTopArticles();

    /**
     * 获取文章列表
     * https://www.wanandroid.com/article/list/0/json
     *
     * @param pageNum
     */
    @GET("article/list/{pageNum}/json")
    Observable<BaseResponse<ArticleListData>> getArticleList(@Path("pageNum") int pageNum);

    /**
     * 知识体系
     * https://www.wanandroid.com/tree/json
     *
     * @return 知识体系数据
     */
    @GET("tree/json")
    Observable<BaseResponse<List<KnowledgeTreeData>>> getKnowledgeTreeData();

    /**
     * 知识体系下的文章
     * https://www.wanandroid.com/article/list/0/json?cid=60
     *
     * @param page page num
     * @param cid  second page id
     * @return 知识体系文章数据
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<KnowItemBean>> getKnowItemData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     *
     * @return 项目分类数据
     */
    @GET("project/tree/json")
    Observable<BaseResponse<List<ProTitleBean>>> getProTitleData();

    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     *
     * @param page page num
     * @param cid  child page id
     * @return 项目列表数据
     */
    @GET("project/list/{page}/json")
    Observable<BaseResponse<ProItemBean>> getProItemData(@Path("page") int page, @Query("cid") int cid);

    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/chapters/json
     *
     * @return 公众号列表数据
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<List<PubTitleBean>>> getPubTitleData();

    /**
     * 获取当前公众号的数据
     * https://wanandroid.com/wxarticle/list/405/1/json
     *
     * @param id
     * @param page
     * @return 获取当前公众号的数据
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<BaseResponse<PubItemBean>> getPubItemData(@Path("id") int id, @Path("page") int page);

    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     *
     * @return 导航数据
     */
    @GET("navi/json")
    Observable<BaseResponse<List<NavigationListData>>> getNavigationListData();

    //https://www.wanandroid.com/friend/json
    @GET("friend/json")
    Observable<BaseResponse<List<FriendBean>>> getFriend();
}
