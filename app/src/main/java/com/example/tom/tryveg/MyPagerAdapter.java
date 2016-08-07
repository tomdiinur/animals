package com.example.tom.tryveg;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tom on 05-Aug-16.
 */
class MyPagerAdapter extends FragmentPagerAdapter {

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int pos) {
        switch(pos) {

            case 0: return PersonalZoneFragment.newInstance("PersonalZoneFragment, Instance 1");
            case 1: return FriendsFragment.newInstance("FriendsFragment, Instance 1");
            case 2: return ShopFragment.newInstance("ShopFragment, Instance 2");
            case 3: return DailyDishFragment.newInstance("DailyDishFragment, Instance 2");
            default: return PersonalZoneFragment.newInstance("PersonalZoneFragment, Default");
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}