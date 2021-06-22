package com.example.soundsbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.soundsbasic.Adapters.SettingsRAdapter;
import com.example.soundsbasic.Models.Settings;

import java.util.ArrayList;
import java.util.List;

public class settingsactivity extends AppCompatActivity {
    RecyclerView recyclerViewsettings;
    LinearLayoutManager layoutManager;
    SettingsRAdapter settingsRAdapter;
    List<Settings> settingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsactivity);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setLogo(R.drawable.settingsiconred);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerViewsettings = findViewById(R.id.settingsrecycle);
        settingsList.add(new Settings(R.drawable.abouticon,"FAQ","Need Help?"));
        settingsList.add(new Settings(R.drawable.aboutlogo,"About","SoundsBasic 1.0"));
        settingsRAdapter = new SettingsRAdapter(settingsactivity.this,settingsList);
        recyclerViewsettings.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewsettings.setAdapter(settingsRAdapter);
        recyclerViewsettings.setHasFixedSize(true);

    }
}