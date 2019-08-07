package com.example.wanandroid_myy.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.fragments.HomeFragment;
import com.example.wanandroid_myy.fragments.LoginFragment;
import com.example.wanandroid_myy.fragments.RegisterFragment;

public class LoginActivity extends AppCompatActivity {

    private View status_bar_view;
    private TextView toolbar_title;
    private Toolbar toolbar;
    private FrameLayout login_frame_layout;
    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        status_bar_view = (View) findViewById(R.id.status_bar_view);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        login_frame_layout = (FrameLayout) findViewById(R.id.login_frame_layout);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        //设置是否显示左侧的按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //给左侧的按钮
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里处理返回按钮的逻辑
                onBackPressed();
            }
        });

        LoginFragment loginFragment = new LoginFragment();
        RegisterFragment registerFragment = new RegisterFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.login_frame_layout, loginFragment);
        transaction.add(R.id.login_frame_layout, registerFragment);
        transaction.show(loginFragment);
        transaction.hide(registerFragment);
        transaction.commit();
        if (loginFragment.isHidden()){
            toolbar_title.setText(R.string.login);
        }else {
            toolbar_title.setText(R.string.register);
        }
    }
}
