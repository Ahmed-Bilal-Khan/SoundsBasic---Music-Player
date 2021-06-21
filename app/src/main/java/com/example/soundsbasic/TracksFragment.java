package com.example.soundsbasic;

import android.Manifest;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class TracksFragment extends Fragment {
    ListView listView1;
    String[] items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tracks,container,false);
        listView1 = view.findViewById(R.id.listvsongs);
        runtimePermission();
        return view;
    }

    public void runtimePermission() {
        Dexter.withContext(getContext()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            displaysongs();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.cancelPermissionRequest();
                    }
                }).check();
    }

    public ArrayList<File> findsongs(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for (File singlefile : files) {
            if (singlefile.isDirectory() && !singlefile.isHidden())
            {
                arrayList.addAll(findsongs(singlefile));
            }
            else
            {
                if(singlefile.getName().endsWith(".mp3") ||singlefile.getName().endsWith(".wav") )
                {
                    arrayList.add(singlefile);
                }
            }
        }
        return arrayList;
    }

    void displaysongs()
    {
        final ArrayList<File> mysongs = findsongs(Environment.getExternalStorageDirectory());
        items = new String[mysongs.size()];
                for (int i=0;i<mysongs.size();i++)
                {
                    items[i] = mysongs.get(i).getName().toString().replace(".mp3","").replace(".wav","");
                }
        /*ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items);
                listView1.setAdapter(myAdapter);*/

        customListVAdapter customListVAdapter = new customListVAdapter();
        listView1.setAdapter(customListVAdapter);
    }

    class customListVAdapter extends BaseAdapter
    {

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
            View myView = getLayoutInflater().inflate(R.layout.list_item,null);
            TextView txtsongn = myView.findViewById(R.id.txtvlist_item);
            txtsongn.setSelected(true);
            txtsongn.setText(items[position]);
            return myView;
        }
    }
}