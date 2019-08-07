package com.example.wanandroid_myy.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class VpTitleAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> title;
    private ArrayList<Fragment> fs;
    public VpTitleAdapter(FragmentManager fm,ArrayList<Fragment> fs,ArrayList<String> title) {
        super(fm);
        this.fs = fs;
        this.title = title;
    }

    @Override
    public Fragment getItem(int i) {
        return fs.get(i);
    }

    @Override
    public int getCount() {
        return fs.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
