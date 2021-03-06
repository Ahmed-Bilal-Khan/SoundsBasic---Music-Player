package com.example.soundsbasic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.soundsbasic.Adapters.SettingsRAdapter;
import com.example.soundsbasic.Models.Settings;

import java.util.ArrayList;
import java.util.List;

public class settingsactivity extends AppCompatActivity {
    /*LinearLayout faq,about;*/

    RecyclerView recyclerViewsettings;
    LinearLayoutManager layoutManager;
    SettingsRAdapter settingsRAdapter;
    /*SettingsRAdapter.RecyclerViewOnClickListener listener;*/
    List<Settings> settingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsactivity);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BA4444")));
        getSupportActionBar().setIcon(R.drawable.settingsicongrey);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*faq.findViewById(R.id.faqlinearlayout);
        about.findViewById(R.id.aboutlinearlayout);

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsactivity.this, faqactitvity.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(settingsactivity.this, About.class));
            }
        }); */

        recyclerViewsettings = findViewById(R.id.settingsrecycle);
        settingsList.add(new Settings(R.drawable.abouticon,"FAQ","Need Help?"));
        settingsList.add(new Settings(R.drawable.aboutlogo,"About","SoundsBasic 1.0"));
        /*listener = new SettingsRAdapter.RecyclerViewOnClickListener() {
            @Override
            public void OnClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), faqactitvity.class);
                startActivity(intent);
            }
        };*/

        settingsRAdapter = new SettingsRAdapter(settingsactivity.this,settingsList);
        recyclerViewsettings.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewsettings.setAdapter(settingsRAdapter);
        recyclerViewsettings.setHasFixedSize(true);

    }
}