package com.example.tom.tryveg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.lzyzsd.circleprogress.ArcProgress;

/**
 * Created by tom on 05-Aug-16.
 */
public class PersonalZoneFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.persnal_zone_layout, container, false);
        ArcProgress progress = (ArcProgress)v.findViewById(R.id.arc_progress);

//        progress.setBottomText(String.valueOf(Globals.currentUser.getDaysVeg()) + "/365");
        int progressValue = Globals.currentUser.getDaysVeg() * 100 / 365;
        progress.setProgress(progressValue);

        return v;
    }

    public static PersonalZoneFragment newInstance(String text) {

        PersonalZoneFragment f = new PersonalZoneFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
