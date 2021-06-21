package com.example.soundsbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.karumi.dexter.Dexter;

public class homepage extends AppCompatActivity {
    TextView txttracks,txtplaylists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportFragmentManager().beginTransaction().add(R.id.containersongs,new TracksFragment()).commit();
        txttracks = findViewById(R.id.trackstxtv);
        txtplaylists = findViewById(R.id.playlisttxtv);
        txtplaylists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containersongs,new PlaylistFragment()).commit();
            }
        });
        txttracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.containersongs,new TracksFragment()).commit();
            }
        });
    }

}