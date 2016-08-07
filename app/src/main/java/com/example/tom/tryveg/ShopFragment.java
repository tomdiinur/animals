package com.example.tom.tryveg;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tom.tryveg.carousel.AppUtils;
import com.example.tom.tryveg.carousel.CarouselView;
import com.example.tom.tryveg.carousel.CarouselViewAdapter;
import com.example.tom.tryveg.carousel.Singleton;
import com.example.tom.tryveg.classes.Pet;

import java.util.ArrayList;

/**
 * Created by tom on 05-Aug-16.
 */
public class ShopFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Singleton m_Inst 					= Singleton.getInstance();
    CarouselViewAdapter m_carouselAdapter		= null;
    private final int		m_nFirstItem			= 1000;

    TextView txtView;
    TextView tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_layout, container, false);

        //no keyboard unless requested by user
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Activity activity = getActivity();
        // compute screen size and scaling
        Singleton.getInstance().InitGUIFrame(activity);

        int padding = m_Inst.Scale(10);
        // create the interface : full screen container
        RelativeLayout panel = (RelativeLayout) v.findViewById(R.id.panel);

        //Create carousel view documents
        ArrayList<Pet> Docus = new ArrayList<Pet>();
        for (int i=0;i<1000;i++) {
            Pet docu;
            docu = Globals.pets.get(i % Globals.pets.size());
            Docus.add(docu);
        }

        // add the serach filter
        txtView = new TextView(activity);
        txtView.setText("אני");
        txtView.setSingleLine();
        txtView.setTextColor(Color.BLACK);
        txtView.setCompoundDrawablesWithIntrinsicBounds(Globals.currentUser.getPetDrawable(), 0, 0, 0);
        AppUtils.AddView(panel, txtView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                new int[][]{new int[]{RelativeLayout.CENTER_HORIZONTAL}, new int[]{RelativeLayout.ALIGN_PARENT_TOP}}, -1, -1);

        // add logo
        tv = new TextView(activity);
        tv.setTextColor(Color.BLACK);
        tv.setText("");
        AppUtils.AddView(panel, tv, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                new int[][]{new int[]{RelativeLayout.CENTER_HORIZONTAL}, new int[]{RelativeLayout.ALIGN_BOTTOM}}, -1,1300);

        // create the carousel
        CarouselView coverFlow = new CarouselView(activity);

        // create adapter and specify device independent items size (scaling)
        // for more details see: http://www.pocketmagic.net/2013/04/how-to-scale-an-android-ui-on-multiple-screens/
        m_carouselAdapter =  new CarouselViewAdapter(activity, Docus, m_Inst.Scale(400),m_Inst.Scale(300));
        coverFlow.setAdapter(m_carouselAdapter);
        coverFlow.setSpacing(-1 * m_Inst.Scale(150));
        coverFlow.setSelection(Integer.MAX_VALUE / 2, true);
        coverFlow.setAnimationDuration(1000);
        coverFlow.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        AppUtils.AddView(panel, coverFlow, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                new int[][]{new int[]{RelativeLayout.CENTER_IN_PARENT}},
                -1, -1);
        return v;
    }

    public static ShopFragment newInstance(String text) {

        ShopFragment f = new ShopFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Pet docu =  (Pet) m_carouselAdapter.getItem((int) arg3);
        if (docu!=null) {
            if (tv != null) {
                if (Globals.currentUser.getDaysVeg() >= docu.getDays()) {
                    tv.setText("You have this animal");
                }
                else {
                    int daysLeft = docu.getDays() - Globals.currentUser.getDaysVeg();
                    tv.setText("more "+ daysLeft + " days to get the " + docu.getName());
                }
            }
//            txtView.setCompoundDrawablesWithIntrinsicBounds(docu.getDrawable(), 0, 0, 0);
//            Toast.makeText(this, "You've clicked on:" + docu.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
