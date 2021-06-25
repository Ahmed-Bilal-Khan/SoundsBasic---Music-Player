package com.example.soundsbasic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gauravk.audiovisualizer.visualizer.BarVisualizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import static com.example.soundsbasic.TracksFragment.shuffleboolean;

public class play_screen extends AppCompatActivity {
    ImageView btnnext, btnprev, btnff, btnfr , btnshuffle, btnRepeat;
    TextView txtsname, txtsstart, txtsstop;
    SeekBar seekmusic;
    Button btnplay;
    BarVisualizer visualizer;
    ImageView imageView;

    LinearLayout musiclinearlayout;
    Animation fadein;
    String sname;
    Thread updateseekbar;

    Boolean repeatflag = false;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    //visualizer not running when no music plays ka check
    @Override
    protected void onDestroy() {
        if (visualizer != null) {
            visualizer.release();
        }
        super.onDestroy();
    }

    public static final String EXTRA_NAME = "song_name";
    static MediaPlayer mediaPlayer;
    int positionn;
    ArrayList<File> mySongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);
        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BA4444")));
        getSupportActionBar().setIcon(R.drawable.musicnotegrey);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        musiclinearlayout = findViewById(R.id.linearlayoutplayer);
        btnprev = findViewById(R.id.BtnPrev);
        btnnext = findViewById(R.id.BtnNext);
        btnplay = findViewById(R.id.playButton);
        btnff = findViewById(R.id.BtnFastfwd);
        btnfr = findViewById(R.id.BtnFastrev);
        btnshuffle = findViewById(R.id.btnshuffle);
        txtsname = findViewById(R.id.playscrn_txt);
        txtsstart = findViewById(R.id.txtsstart);
        txtsstop = findViewById(R.id.txtsstop);
        seekmusic = findViewById(R.id.seekbar);
        visualizer = findViewById(R.id.blast);
        imageView = findViewById(R.id.playscrn_img);
        btnRepeat = findViewById(R.id.BtnRepeat);

        fadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fazein);
        musiclinearlayout.setAnimation(fadein);

        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        mySongs = (ArrayList) bundle.getParcelableArrayList("songs");
        String songName = i.getStringExtra("songname");
        positionn = bundle.getInt("pos", 0);
        txtsname.setSelected(true);
        Uri uri = Uri.parse(mySongs.get(positionn).toString());
        sname = mySongs.get(positionn).getName();
        txtsname.setText(sname);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
        mediaPlayer.start();

        updateseekbar = new Thread()
        {
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentpostion = 0;

                while (currentpostion<totalDuration)
                {
                    try {
                        sleep(500);
                        currentpostion = mediaPlayer.getCurrentPosition();
                        seekmusic.setProgress(currentpostion);
                    }
                    catch (InterruptedException | IllegalStateException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        };

        seekmusic.setMax(mediaPlayer.getDuration());
        updateseekbar.start();
        seekmusic.getProgressDrawable().setColorFilter(getResources().getColor(R.color.logored), PorterDuff.Mode.MULTIPLY);
        seekmusic.getThumb().setColorFilter(getResources().getColor(R.color.logored), PorterDuff.Mode.SRC_IN);

        //seekbarchange when user moves it
        seekmusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btnshuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shuffleboolean)
                {
                    shuffleboolean = false;
                    Toast.makeText(getApplicationContext(),"Shuffle is OFF",Toast.LENGTH_LONG).show();
                    btnshuffle.setImageResource(R.drawable.shuffleiconoff);
                }
                else
                {
                    shuffleboolean = true;
                    Toast.makeText(getApplicationContext(),"Shuffle is ON",Toast.LENGTH_LONG).show();
                    btnshuffle.setImageResource(R.drawable.shuffle_icon);
                }
            }
        });

        String endTime = createTime(mediaPlayer.getDuration());
        txtsstop.setText(endTime);

        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String currentTime = createTime(mediaPlayer.getCurrentPosition());
                txtsstart.setText(currentTime);
                handler.postDelayed(this, delay);
            }
        }, delay);


        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    btnplay.setBackgroundResource(R.drawable.play_arrow);
                    mediaPlayer.pause();
                }
                else
                {
                    btnplay.setBackgroundResource(R.drawable.pause_icon);
                    mediaPlayer.start();
                }
            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();

                if(shuffleboolean)
                {
                    positionn = getRandom(mySongs.size()-1);
                }

                else {
                    positionn = ((positionn + 1) % mySongs.size());
                }
                Uri u = Uri.parse(mySongs.get(positionn).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(positionn).getName();
                txtsname.setText(sname);
                mediaPlayer.start();
                btnplay.setBackgroundResource(R.drawable.pause_icon);
                //startAnimation(imageView);

                int audiosessionID = mediaPlayer.getAudioSessionId();
                if (audiosessionID != -1)
                {
                    visualizer.setAudioSessionId(audiosessionID);
                }
            }
            private int getRandom(int i)
            {
                Random random = new Random();
                return random.nextInt(i+1);
            }

        });



        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                if(shuffleboolean)
                {
                    positionn = getRandom(mySongs.size()-1);
                }

                else {
                    positionn = ((positionn - 1)<0)?(mySongs.size()-1):(positionn-1);
                }
                Uri u = Uri.parse(mySongs.get(positionn).toString());
                mediaPlayer = MediaPlayer.create(getApplicationContext(), u);
                sname = mySongs.get(positionn).getName();
                txtsname.setText(sname);
                mediaPlayer.start();
                btnplay.setBackgroundResource(R.drawable.pause_icon);
                //startAnimation(imageView);

                int audiosessionID = mediaPlayer.getAudioSessionId();
                if (audiosessionID != -1)
                {
                    visualizer.setAudioSessionId(audiosessionID);
                }
            }
            private int getRandom(int i)
            {
                Random random = new Random();
                return random.nextInt(i+1);
            }
        });

        //next song after one song is complete
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

                if (repeatflag){
                    btnplay.performClick();
                    Toast.makeText(getApplicationContext(),"Playing Again",Toast.LENGTH_LONG).show();
                }
                else
                    {
                    btnnext.performClick();
                }

            }
        });

        int audiosessionID = mediaPlayer.getAudioSessionId();
        if (audiosessionID != -1)
        {
            visualizer.setAudioSessionId(audiosessionID);
        }

        btnff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                }
            }
        });

        btnfr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                }
            }
        });
    }



  /*  public void startAnimation(View view)
    {
        @SuppressLint("ObjectAnimatorBinding") ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotation, 0f,360f");
        animator.setDuration(1000);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator);
        animatorSet.start();
    } */

    //Converting time from milli secs to minutes and secs ftn
    public String createTime(int duration)
    {
        String time = "";
        int min = duration/1000/60;
        int sec = duration/1000%60;

        time+=min+":";

        if (sec<10)
        {
            time+="0";
        }
        time+=sec;

        return time;
    }

    public void repeatsong(View view) {
        if (repeatflag){

            btnRepeat.setImageResource(R.drawable.repeat_vector);
            Toast.makeText(getApplicationContext(),"Repeat OFF",Toast.LENGTH_LONG).show();

        }
        else
            {
                btnRepeat.setImageResource(R.drawable.repeat_on);
                Toast.makeText(getApplicationContext(),"Repeat ON",Toast.LENGTH_LONG).show();

        }
        repeatflag = !repeatflag;
    }
}