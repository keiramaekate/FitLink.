package com.example.fitlinkv3;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

        private int numOfTabs;

        // This java code is used to
        public PagerAdapter(FragmentManager fm, int numOfTabs) {
            super(fm);
            this.numOfTabs = numOfTabs;

        }

        // Initialize fragments for Android TabLayout
        @Override
        public Fragment getItem ( int position) {
            switch (position) {
                case 0:
                    return new ActivityFragment();
                case 1:
                    return new AchievementsFragment();
                default:
                    return null;
            }
        }

        // Returns number of tabs listed within the tablayout
            @Override
            public int getCount() {
                return numOfTabs;
            }
        }