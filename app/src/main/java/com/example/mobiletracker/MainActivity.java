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
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

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


    public void proceed(View view) {

        Intent intent = new Intent(this, loginmenu.class);

       startActivity(intent);

    }


}
