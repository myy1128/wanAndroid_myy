package com.example.wanandroid_myy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wanandroid_myy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {


    private View view;
    /**
     * 用户名
     */
    private EditText et_username;
    /**
     * 密码
     */
    private EditText et_password;
    /**
     * 登录
     */
    private Button btn_login;
    /**
     * 没有账号？前去注册
     */
    private TextView tv_sign_up;
    private FragmentManager manager;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        //在此处传递参数，可在fragment恢复时使用；避免在构造函数中传参，fragment恢复时不调用非默认构造函数
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_login, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        et_username = (EditText) inflate.findViewById(R.id.et_username);
        et_password = (EditText) inflate.findViewById(R.id.et_password);
        btn_login = (Button) inflate.findViewById(R.id.btn_login);
        tv_sign_up = (TextView) inflate.findViewById(R.id.tv_sign_up);

        btn_login.setOnClickListener(this);
        tv_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_login:
                break;
            case R.id.tv_sign_up:
                RegisterFragment registerFragment = new RegisterFragment();
                manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.login_frame_layout, registerFragment);
                transaction.commit();
                break;
        }
    }
}
