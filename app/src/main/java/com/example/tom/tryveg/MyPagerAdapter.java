package com.example.tom.tryveg;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tom.tryveg.Fragments.ChallengeFragment;
import com.example.tom.tryveg.Fragments.DailyDishFragment;
import com.example.tom.tryveg.Fragments.FriendsFragment;
import com.example.tom.tryveg.Fragments.PersonalZoneFragment;
import com.example.tom.tryveg.Fragments.ShopFragment;

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
            case 2: return ChallengeFragment.newInstance("ChallengeFragment, Instance 2");
            case 3: return ShopFragment.newInstance("ShopFragment, Instance 2");
            case 4: return DailyDishFragment.newInstance("DailyDishFragment, Instance 2");
            default: return PersonalZoneFragment.newInstance("PersonalZoneFragment, Default");
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}