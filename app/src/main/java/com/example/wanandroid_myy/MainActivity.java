package com.example.wanandroid_myy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wanandroid_myy.activitys.AboutActivity;
import com.example.wanandroid_myy.activitys.CommonActivity;
import com.example.wanandroid_myy.activitys.LoginActivity;
import com.example.wanandroid_myy.activitys.SettingActivity;
import com.example.wanandroid_myy.base.BaseActivity;
import com.example.wanandroid_myy.base.BasePresenter;
import com.example.wanandroid_myy.fragments.HomeFragment;
import com.example.wanandroid_myy.fragments.KnowledgeFragment;
import com.example.wanandroid_myy.fragments.NavFragment;
import com.example.wanandroid_myy.fragments.ProFragment;
import com.example.wanandroid_myy.fragments.PubFragment;
import com.example.wanandroid_myy.util.Constants;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private FloatingActionButton arrow_btn;
    private NavigationView nv;
    private DrawerLayout dl;

    private ArrayList<Integer> titles;
    private ArrayList<Fragment> fs;
    private int mCurrentFgIndex = 0;
    private View status_bar_view;
    private TextView toolbar_title;
    private Toolbar toolbar;
    private FrameLayout fragment_group;
    private BottomNavigationView bottom_navigation_view;
    private int mLastFgIndex = -1;
    private HomeFragment mHomePagerFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private NavFragment mNavigationFragment;
    private PubFragment mWxArticleFragment;
    private ProFragment mProjectFragment;
    TextView mUsTv;
    private SharedPreferences mPreferences;


    @Override
    protected void initView() {
        arrow_btn = (FloatingActionButton) findViewById(R.id.arrow_btn);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        status_bar_view = (View) findViewById(R.id.status_bar_view);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fragment_group = (FrameLayout) findViewById(R.id.fragment_group);
        bottom_navigation_view = (BottomNavigationView) findViewById(R.id.bottom_navigation_view);
        mPreferences = this.getSharedPreferences(Constants.MY_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            toolbar_title.setText(R.string.home_pager);
        }
//      三个杠
        initDrawerLayout();
//      fragment切换
        showFragment(mCurrentFgIndex);
        initNavigationView();
        initBottomNavigationView();
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    private void initBottomNavigationView() {
        bottom_navigation_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_main_pager:
                        showFragment(Constants.TYPE_HOME_PAGER);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        showFragment( Constants.TYPE_KNOWLEDGE);
                        break;
                    case R.id.tab_navigation:
                        showFragment(Constants.TYPE_NAVIGATION);
                        break;
                    case R.id.tab_wx_article:
                        showFragment(Constants.TYPE_WX_ARTICLE);
                        break;
                    case R.id.tab_project:
                        showFragment(Constants.TYPE_PROJECT);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void initNavigationView() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.my_collect:
                        /*if (mPresenter.getLoginStatus()) {
                            CommonUtils.startFragmentInCommonActivity(MainActivity.this, Constants.TYPE_COLLECT);
                        } else {
                            CommonUtils.startLoginActivity(MainActivity.this);
                            ToastUtils.showToast(MainActivity.this, getString(R.string.login_first));
                        }*/
                        break;
                    case R.id.todo:
                        /*if (mPresenter.getLoginStatus()) {
                            Intent intent = new Intent(MainActivity.this, TodoActivity.class);
                            startActivity(intent);
                        } else {
                            CommonUtils.startLoginActivity(MainActivity.this);
                            ToastUtils.showToast(MainActivity.this, getString(R.string.login_first));
                        }*/
                        break;
                    case R.id.night:
                        if (isNightMode()) {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                            setNightMode(false);
                            menuItem.setTitle(R.string.nav_day_mode);
                        } else {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                            setNightMode(true);
                            menuItem.setTitle(R.string.nav_night_mode);
                        }
                        recreate();
                        break;
                    case R.id.setting:
                        startActivity(new Intent(MainActivity.this,SettingActivity.class));
                        break;
                    case R.id.about:
                        startActivity(new Intent(MainActivity.this,AboutActivity.class));
                        break;
                    case R.id.logout:
//                        mPresenter.logout();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mUsTv = nv.getHeaderView(0).findViewById(R.id.nav_header_login);
//        mUsTv.setText(mPresenter.getLoginStatus() ? mPresenter.getLoginAccount() : getString(R.string.login));
        mUsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        nv.getMenu().findItem(R.id.logout);
        MenuItem nightModeItem = nv.getMenu().findItem(R.id.night);
        if (isNightMode()) {
            nightModeItem.setIcon(R.drawable.ic_day);
            nightModeItem.setTitle(R.string.nav_day_mode);
        } else {
            nightModeItem.setIcon(R.drawable.ic_night);
            nightModeItem.setTitle(R.string.nav_night_mode);
        }
    }

    private void showFragment(int index) {
        mCurrentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mLastFgIndex = index;
        switch (index) {
            case Constants.TYPE_HOME_PAGER:
                toolbar_title.setText(getString(R.string.home_pager));
                if (mHomePagerFragment == null) {
                    mHomePagerFragment = HomeFragment.newInstance();
                    transaction.add(R.id.fragment_group, mHomePagerFragment);
                }
                transaction.show(mHomePagerFragment);
                break;
            case Constants.TYPE_KNOWLEDGE:
                toolbar_title.setText(getString(R.string.knowledge_hierarchy));
                if (mKnowledgeFragment == null) {
                    mKnowledgeFragment = KnowledgeFragment.newInstance();
                    transaction.add(R.id.fragment_group, mKnowledgeFragment);
                }
                transaction.show(mKnowledgeFragment);
                break;
            case Constants.TYPE_NAVIGATION:
                toolbar_title.setText(getString(R.string.navigation));
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavFragment.newInstance();
                    transaction.add(R.id.fragment_group, mNavigationFragment);
                }
                transaction.show(mNavigationFragment);
                break;
            case Constants.TYPE_WX_ARTICLE:
                toolbar_title.setText(getString(R.string.wx_article));
                if (mWxArticleFragment == null) {
                    mWxArticleFragment = PubFragment.newInstance();
                    transaction.add(R.id.fragment_group, mWxArticleFragment);
                }
                transaction.show(mWxArticleFragment);
                break;
            case Constants.TYPE_PROJECT:
                toolbar_title.setText(getString(R.string.project));
                if (mProjectFragment == null) {
                    mProjectFragment = ProFragment.newInstance();
                    transaction.add(R.id.fragment_group, mProjectFragment);
                }
                transaction.show(mProjectFragment);
                break;

            default:

                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        switch (mLastFgIndex) {
            case Constants.TYPE_HOME_PAGER:
                if (mHomePagerFragment != null) {
                    transaction.hide(mHomePagerFragment);
                }
                break;
            case Constants.TYPE_KNOWLEDGE:
                if (mKnowledgeFragment != null) {
                    transaction.hide(mKnowledgeFragment);
                }
                break;
            case Constants.TYPE_NAVIGATION:
                if (mNavigationFragment != null) {
                    transaction.hide(mNavigationFragment);
                }
                break;
            case Constants.TYPE_WX_ARTICLE:
                if (mWxArticleFragment != null) {
                    transaction.hide(mWxArticleFragment);
                }
                break;
            case Constants.TYPE_PROJECT:
                if (mProjectFragment != null) {
                    transaction.hide(mProjectFragment);
                }
                break;
            default:
                break;
        }
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        dl.addDrawerListener(toggle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.CURRENT_FRAGMENT_KEY, mCurrentFgIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_usage:
                intent = new Intent(MainActivity.this, CommonActivity.class);
                intent.putExtra(Constants.TYPE_FRAGMENT_KEY, Constants.TYPE_USEFULSITES);
                startActivity(intent);
                break;
            case R.id.action_search:
                /*intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);*/
                break;
            default:
                break;
        }
        return true;
    }
    public void setNightMode(boolean isNightMode) {
        mPreferences.edit().putBoolean(Constants.NIGHT_MODE, isNightMode).apply();
    }

    public boolean isNightMode() {
        return mPreferences.getBoolean(Constants.NIGHT_MODE, false);
    }
}
