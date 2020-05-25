package com.trump.library_common.ui.adapter;

import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author 王元_Trump
 * @time 2020/03/26 16:36
 * @desc
 */
public class TabLayoutAdapter extends FragmentPagerAdapter {
    private ArrayList<String> list_title;
    private ArrayList<Fragment> list_fragment;

    public TabLayoutAdapter(FragmentManager fm, ArrayList<String> list_title, ArrayList<Fragment> list_fragment) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.list_title = list_title;
        this.list_fragment = list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_fragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
    }
}