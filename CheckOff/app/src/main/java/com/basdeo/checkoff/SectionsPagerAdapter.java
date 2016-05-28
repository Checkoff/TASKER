package com.basdeo.checkoff;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Eugene on 3/27/14.
 */
// FragmentPagerAdapter that returns a fragment
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String[] titles = {"Setup"};

    public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new setupFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // Show 1 total pages.
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];

    }
}
