package com.example.wanandroid_myy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
public class RegisterFragment extends Fragment implements View.OnClickListener {


    private View view;
    /**
     * 用户名
     */
    private EditText zhu_et_username;
    /**
     * 密码
     */
    private EditText zhu_et_password;
    /**
     * 确认密码
     */
    private EditText zhu_et_password2;
    /**
     * 登录
     */
    private Button zhu_btn_login;
    /**
     * 已有账号，前去登录
     */
    private TextView zhu_tv_sign_up;
    private FragmentManager manager;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        //在此处传递参数，可在fragment恢复时使用；避免在构造函数中传参，fragment恢复时不调用非默认构造函数
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_register, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        zhu_et_username = (EditText) inflate.findViewById(R.id.zhu_et_username);
        zhu_et_password = (EditText) inflate.findViewById(R.id.zhu_et_password);
        zhu_et_password2 = (EditText) inflate.findViewById(R.id.zhu_et_password2);
        zhu_btn_login = (Button) inflate.findViewById(R.id.zhu_btn_login);
        zhu_tv_sign_up = (TextView) inflate.findViewById(R.id.zhu_tv_sign_up);

        zhu_btn_login.setOnClickListener(this);
        zhu_tv_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.zhu_btn_login:

                break;
            case R.id.zhu_tv_sign_up:
                LoginFragment loginFragment = new LoginFragment();
                manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.login_frame_layout, loginFragment);
                transaction.commit();
                break;
        }
    }
}
