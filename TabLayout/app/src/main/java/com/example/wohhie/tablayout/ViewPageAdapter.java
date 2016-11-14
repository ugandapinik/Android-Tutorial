package com.example.wohhie.tablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by wohhi_000 on 11/12/2016.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> tabTitles = new ArrayList<>();


    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addFragments(Fragment fragments, String titles){
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public CharSequence getPageTitle(int position){
        return tabTitles.get(position);
    }
}
