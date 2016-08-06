package com.example.tom.tryveg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by tom on 05-Aug-16.
 */
public class DailyDishFragment extends Fragment {
    private ExplosionField mExplosionField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.daily_dish_layout, container, false);
//        ShimmerFrameLayout shimmerContainer =
//                (ShimmerFrameLayout) v.findViewById(R.id.shimmer_view_container);
//        shimmerContainer.startShimmerAnimation();
        mExplosionField = ExplosionField.attach2Window(getActivity());
        TextView txt = (TextView)v.findViewById(R.id.txt_qoute);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExplosionField.explode(v);
                v.setOnClickListener(null);
            }
        });
        return v;
    }

    public static DailyDishFragment newInstance(String text) {

        DailyDishFragment f = new DailyDishFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
}
