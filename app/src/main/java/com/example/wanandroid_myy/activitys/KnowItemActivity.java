package com.example.wanandroid_myy.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.adapters.VpTitleAdapter;
import com.example.wanandroid_myy.bean.KnowledgeTreeData;
import com.example.wanandroid_myy.fragments.KnowItemFragment;

import java.util.ArrayList;
import java.util.List;

public class KnowItemActivity extends AppCompatActivity {

    private TabLayout know_item_tab;
    private ViewPager know_item_vp;
    private static final String TAG = "KnowItemActivity";
    private ArrayList<Fragment> fs;
    private ArrayList<String> title;
    private View status_bar_view;
    private TextView toolbar_title;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_item);
        initView();
    }

    private void initView() {
        know_item_tab = (TabLayout) findViewById(R.id.know_item_tab);
        know_item_vp = (ViewPager) findViewById(R.id.know_item_vp);
        status_bar_view = (View) findViewById(R.id.status_bar_view);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        KnowledgeTreeData data = (KnowledgeTreeData) intent.getSerializableExtra("data");
        List<KnowledgeTreeData.ChildrenBean> children = data.getChildren();

        toolbar_title.setText(data.getName());

        fs = new ArrayList<>();
        title = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            title.add(children.get(i).getName());
            KnowItemFragment knowItemFragment = new KnowItemFragment();
            Bundle bundle = new Bundle();
            int id = children.get(i).getId();
            bundle.putInt("id", id);
            knowItemFragment.setArguments(bundle);
            fs.add(knowItemFragment);
        }
        VpTitleAdapter vpTitleAdapter = new VpTitleAdapter(getSupportFragmentManager(), fs, title);
        know_item_vp.setAdapter(vpTitleAdapter);
        know_item_tab.setupWithViewPager(know_item_vp);

    }
}
