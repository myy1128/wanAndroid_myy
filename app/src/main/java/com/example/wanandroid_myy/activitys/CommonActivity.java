/*
 *     (C) Copyright 2019, ForgetSky.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.example.wanandroid_myy.activitys;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.adapters.FriendRvAdapter;
import com.example.wanandroid_myy.base.BaseActivity;
import com.example.wanandroid_myy.bean.FriendBean;
import com.example.wanandroid_myy.contract.FriendContract;
import com.example.wanandroid_myy.presenter.FriendPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * @author ForgetSky
 * @date 19-2-25
 */
public class CommonActivity<V extends FriendContract.FriendView> extends BaseActivity<FriendContract.FriendView, FriendPresenter<FriendContract.FriendView>> implements FriendContract.FriendView, FriendRvAdapter.OnClickListener {


    private View status_bar_view;
    private TextView toolbar_title;
    private Toolbar toolbar;
    private RecyclerView friend_rv;
    private ArrayList<FriendBean> list;
    private FriendRvAdapter friendRvAdapter;
    private static final String TAG = "CommonActivity";

    @Override
    protected void initView() {
        status_bar_view = (View) findViewById(R.id.status_bar_view);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        friend_rv = (RecyclerView) findViewById(R.id.friend_rv);

        friend_rv.setLayoutManager(new LinearLayoutManager(this));

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        toolbar_title.setText(R.string.useful_sites);

        //设置是否显示左侧的按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //给左侧的按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里处理返回按钮的逻辑
                finish();
            }
        });

        list = new ArrayList<>();
        friendRvAdapter = new FriendRvAdapter(list, this);
        friend_rv.setAdapter(friendRvAdapter);

        mPresenter.http();

        friendRvAdapter.setOnClickListener(this);
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_common;
    }

    @Override
    public void showSuccess(List<FriendBean> data) {
        list.addAll(data);
        friendRvAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        Log.e(TAG, "showError: " + error);
    }

    @Override
    protected FriendPresenter createPresenter() {
        return new FriendPresenter();
    }

    @Override
    public void OnItemClick(int p, FriendBean dataBean) {
        Intent intent = new Intent(this, ParticularsActivity.class);
        intent.putExtra("title", dataBean.getName());
        intent.putExtra("link", dataBean.getLink());
        startActivity(intent);
    }
}
