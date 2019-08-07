package com.example.wanandroid_myy.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.wanandroid_myy.R;
import com.example.wanandroid_myy.util.Constants;
import com.example.wanandroid_myy.util.ToastUtils;

import java.lang.reflect.Method;

public class ParticularsActivity extends AppCompatActivity {

    private View status_bar_view;
    private TextView toolbar_title;
    private Toolbar toolbar;
    private WebView web;
    private MenuItem mCollectItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particulars);
        initView();
    }

    private void initView() {
        status_bar_view = (View) findViewById(R.id.status_bar_view);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        web = (WebView) findViewById(R.id.web);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String link = intent.getStringExtra("link");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar_title.setText(title);

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

        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(link);
        web = (WebView) findViewById(R.id.web);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acticle_detail, menu);
        mCollectItem = menu.findItem(R.id.item_collect);
        mCollectItem.setVisible(true);
        mCollectItem.setIcon(R.drawable.ic_like_not_white);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
//                mPresenter.shareEventWithPermissionVerify(new RxPermissions(this));
                break;
            case R.id.item_collect:
//                collectClickEvent();
                break;
            case R.id.item_system_browser:
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (Constants.MENU_BUILDER.equalsIgnoreCase(menu.getClass().getSimpleName())) {
                try {
                    @SuppressLint("PrivateApi")
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
