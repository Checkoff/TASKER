package com.basdeo.checkoff.Handlers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.basdeo.checkoff.Fragments.dashboardFragment;
import com.basdeo.checkoff.Fragments.settingsFragment;
import com.basdeo.checkoff.Fragments.taskmanagementFragment;

import java.util.ArrayList;
import java.util.List;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private String[] titles = new String[]{"Dashboard", "Settings", "Task Management"};

    public SectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new dashboardFragment();
            case 1: // Fragment # 0 - This will show SecondFragment different title
                return new settingsFragment();
            case 2: // Fragment # 1 - This will show ThirdFragment
                return new taskmanagementFragment();
            default:
                return null;
        }
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}