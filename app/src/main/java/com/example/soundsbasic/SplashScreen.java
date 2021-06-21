package com.example.soundsbasic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView txtvlogo;
    ImageView logoimg;
    Animation fazein;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        txtvlogo = findViewById(R.id.txtvlogo);
        logoimg = findViewById(R.id.logoimgv);

        fazein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fazein);
        txtvlogo.setAnimation(fazein);
        logoimg.setAnimation(fazein);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), homepage.class));
                finish();
            }
        },2000);
    }
}