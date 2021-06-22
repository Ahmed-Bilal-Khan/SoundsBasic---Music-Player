package com.example.soundsbasic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class FavoritesFragment extends Fragment {
    ListView favsongs;
    static String[] items;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        /*favsongs = view.findViewById(R.id.listvfavsongs);

        Bundle bundle = this.getArguments();
        String song = bundle.getString("song");

        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),R.layout.fragment_favorites);
        /*FavoritesFragment.customListVAdapter customListVAdapter = new FavoritesFragment.customListVAdapter();
        favsongs.setAdapter(adapter);*/
        return view;
    }


    /*class customListVAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View myView = getLayoutInflater().inflate(R.layout.list_item, null);
            TextView txtsongn = myView.findViewById(R.id.txtvlist_item);
            txtsongn.setSelected(true);
            txtsongn.setText(items[position]);
            return myView;
        }
    }*/
}