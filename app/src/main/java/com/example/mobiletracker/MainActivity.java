package com.example.mobiletracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();


        //flag for making android layout a full screan
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        //gets video from xml file and starts it in background
        VideoView videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.background);


        videoview.setVideoURI(uri);

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoview.start();
    }


    //this plays video again when we revisit the same view.
    @Override
    public void onResume(){
        super.onResume();

        //retrieves video and plays it again
        VideoView videoview = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.background);


        videoview.setVideoURI(uri);


//plays it on continous loop
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
//starts video
        videoview.start();

    }


    //goes into next window which is login screan.
    public void proceed(View view) {

        Intent intent = new Intent(this, loginmenu.class);

       startActivity(intent);

    }


}
