package com.example.wanandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.app.Constants;
import com.example.wanandroid.base.BaseActivity;
import com.example.wanandroid.base.SimpleActivity;
import com.example.wanandroid.bean.ListData;
import com.example.wanandroid.contract.MainContract;
import com.example.wanandroid.fragments.HomePagerFragment;
import com.example.wanandroid.fragments.KnowledgeFragment;
import com.example.wanandroid.fragments.NavigationFragment;
import com.example.wanandroid.fragments.ProjectFragment;
import com.example.wanandroid.fragments.WxArticleFragment;
import com.example.wanandroid.persenter.Persenterimpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends SimpleActivity implements MainContract.MainView {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_group)
    FrameLayout fragmentGroup;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mainFloatingActionBtn;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private HomePagerFragment homePagerFragment;
    private KnowledgeFragment knowledgeFragment;
    private NavigationFragment navigationFragment;
    private WxArticleFragment wxArticleFragment;
    private ProjectFragment projectFragment;
    private int mLastFgIndex = -1;
    private int mCurrentFgIndex = 0;


    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();
        drawerLayout.addDrawerListener(toggle);
        //  侧滑 菜单
        initNavigationView();
        //  显示添加 fragment
        showFragment(mCurrentFgIndex);
        //底部點擊監聽
        initBottomNavigationView();


    }
    //底部點擊監聽
    private void initBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_main_pager:
                        showFragment(Constants.TYPE_HOME_PAGER);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        showFragment(Constants.TYPE_KNOWLEDGE);
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

    // 显示点击的fragment
    private void showFragment(int index) {
        mCurrentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mLastFgIndex = index;
        switch (index) {
            case Constants.TYPE_HOME_PAGER:
                toolbarTitle.setText(R.string.home_pager);
                if (homePagerFragment == null) {
                    homePagerFragment = new HomePagerFragment();
                    transaction.add(R.id.fragment_group, homePagerFragment);
                }
                transaction.show(homePagerFragment);
                break;
            case Constants.TYPE_KNOWLEDGE:
                toolbarTitle.setText(R.string.knowledge_hierarchy);
                if (knowledgeFragment == null) {
                    knowledgeFragment = new KnowledgeFragment();
                    transaction.add(R.id.fragment_group, knowledgeFragment);
                }
                transaction.show(knowledgeFragment);
                break;
            case Constants.TYPE_NAVIGATION:
                toolbarTitle.setText(R.string.navigation);
                if (navigationFragment == null) {
                    navigationFragment = new NavigationFragment();
                    transaction.add(R.id.fragment_group, navigationFragment);
                }
                transaction.show(navigationFragment);
                break;
            case Constants.TYPE_WX_ARTICLE:
                toolbarTitle.setText(R.string.wx_article);
                if (wxArticleFragment == null) {
                    wxArticleFragment = new WxArticleFragment();
                    transaction.add(R.id.fragment_group, wxArticleFragment);
                }
                transaction.show(wxArticleFragment);
                break;
            case Constants.TYPE_PROJECT:
                toolbarTitle.setText(R.string.project);
                if (projectFragment == null) {
                    projectFragment = new ProjectFragment();
                    transaction.add(R.id.fragment_group, projectFragment);
                }
                transaction.show(projectFragment);
                break;

            default:
                break;
        }
        transaction.commit();

    }

    //  隐藏最后一次显示的 Fragment
    private void hideFragment(FragmentTransaction transaction) {
        switch (mLastFgIndex) {
            case Constants.TYPE_HOME_PAGER:
                if (homePagerFragment != null) {
                    transaction.hide(homePagerFragment);
                }
                break;
            case Constants.TYPE_KNOWLEDGE:
                if (knowledgeFragment != null) {
                    transaction.hide(knowledgeFragment);
                }
                break;
            case Constants.TYPE_NAVIGATION:
                if (navigationFragment != null) {
                    transaction.hide(navigationFragment);
                }
                break;
            case Constants.TYPE_WX_ARTICLE:
                if (wxArticleFragment != null) {
                    transaction.hide(wxArticleFragment);
                }
                break;
            case Constants.TYPE_PROJECT:
                if (projectFragment != null) {
                    transaction.hide(projectFragment);
                }
                break;

            default:
                break;
        }
    }

    private void initNavigationView() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_item_my_collect:

                        break;

                    default:
                        break;

                }
                return true;
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }



    @Override
    public void showSuccesses(Object o) {

    }

    @Override
    public void showError(String error) {

    }
}
