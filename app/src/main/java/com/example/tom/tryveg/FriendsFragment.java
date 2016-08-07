package com.example.tom.tryveg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

        if (friendList.size() == 0) {
            friendList.addAll(Globals.friends);
        }
        adapter.notifyDataSetChanged();
//        registerForContextMenu(listView);

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.friends_list) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_layout, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.add:
                // add stuff here
                return true;
            case R.id.edit:
                // edit stuff here
                return true;
            case R.id.delete:
                // remove stuff here
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
