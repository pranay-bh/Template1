package com.solvedunsloved.template1.Adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.solvedunsloved.template1.fragments.BlankFragment;
import com.solvedunsloved.template1.fragments.Fragment2;
import com.solvedunsloved.template1.fragments.Fragment3;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null ;
        switch (position){
            case 0 : fragment = new BlankFragment();break;
            case 1 : fragment = new Fragment2();break;
            case 2 : fragment = new Fragment3(); break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0 : title = "Section 1"; break;
            case 1 : title = "Section 2"; break;
            case 2 : title = "Section 4"; break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}