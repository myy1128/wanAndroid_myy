package com.example.wanandroid_myy.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid_myy.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private View status_bar_view;
    private TextView toolbar_title;
    private Toolbar toolbar;
    /**
     * wanAndroid_myy
     */
    private TextView about_version;
    private CardView about_upgrade;
    private CardView about_website;
    private CardView about_source_code;
    private CardView about_feedback;
    private CardView about_copyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        status_bar_view = (View) findViewById(R.id.status_bar_view);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        about_version = (TextView) findViewById(R.id.about_version);
        about_upgrade = (CardView) findViewById(R.id.about_upgrade);
        about_website = (CardView) findViewById(R.id.about_website);
        about_source_code = (CardView) findViewById(R.id.about_source_code);
        about_feedback = (CardView) findViewById(R.id.about_feedback);
        about_copyright = (CardView) findViewById(R.id.about_copyright);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar_title.setText(R.string.about_us);
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
        about_upgrade.setOnClickListener(this);
        about_website.setOnClickListener(this);
        about_source_code.setOnClickListener(this);
        about_feedback.setOnClickListener(this);
        about_copyright.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.about_upgrade:
                Toast.makeText(this, "当前已是最新版本", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_website:
                break;
            case R.id.about_source_code:
                break;
            case R.id.about_feedback:
                break;
            case R.id.about_copyright:
                break;
        }
    }
}
