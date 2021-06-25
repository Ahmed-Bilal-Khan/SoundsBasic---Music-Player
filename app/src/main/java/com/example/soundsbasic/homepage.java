package com.example.soundsbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;

import java.util.ArrayList;

public class homepage extends AppCompatActivity {
    TextView txttracks,txtplaylists,txtfavorites;
    ImageView imgvsettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getSupportActionBar().hide();
        /*getSupportActionBar().setTitle("SoundsBasic");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BA4444"))); */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().add(R.id.containersongs,new TracksFragment()).commit();
        txttracks = findViewById(R.id.trackstxtv);
        txtplaylists = findViewById(R.id.playlisttxtv);
        txtfavorites = findViewById(R.id.favortxtv);
        imgvsettings = findViewById(R.id.settingsimgvgrey);

        imgvsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), settingsactivity.class));
            }
        });

        txtplaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containersongs,new PlaylistFragment()).commit();
            }
        });

        txtfavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containersongs,new FavoritesFragment()).commit();
            }
        });

        txttracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containersongs,new TracksFragment()).commit();
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        /*MenuItem menuItem = menu.findItem(R.id.searchoption);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }*/

     /*@Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    } */
}