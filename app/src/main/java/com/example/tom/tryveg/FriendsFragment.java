package com.example.tom.tryveg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.tom.tryveg.classes.FacebookFriend;
import com.example.tom.tryveg.friends.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 05-Aug-16.
 */
public class FriendsFragment extends Fragment {

    private List<FacebookFriend> friendList = new ArrayList<>();
    private ListView listView;
    private CustomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.friends_layout, container, false);

        listView = (ListView) v.findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), friendList, getContext());
        listView.setAdapter(adapter);

        friendList.addAll(Globals.friends);
        adapter.notifyDataSetChanged();

        return v;
    }

    public static FriendsFragment newInstance(String text) {

        FriendsFragment f = new FriendsFragment();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
