package com.example.mobiletracker;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class login extends AppCompatActivity {

    private ConstraintLayout rootLayout;
    private AnimationDrawable animDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

//        imageView.setImageDrawable(getResource().getDrawable(R.drawable.animation);
//        AnimationDrawable animation = (AnimationDrawable)imageView.getDrawable();
//        animation.start();
//
//
//        AnimationDrawable animDrawable = (AnimationDrawable) findViewById(R.drawable.gradient_animation);
//        animDrawable.setEnterFadeDuration(10);
//        animDrawable.setExitFadeDuration(5000);
//        animDrawable.start();



//        ImageView img = (ImageView)findViewById(R.id.imageView);
//        img.setBackgroundResource(R.drawable.gradient_animation);
//
//        // Get the background, which has been compiled to an AnimationDrawable object.
//        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
//        frameAnimation.setEnterFadeDuration(1);
//        frameAnimation.setExitFadeDuration(400);
//        // Start the animation (looped playback by default).
//        frameAnimation.start();
        rootLayout = (ConstraintLayout) findViewById(R.id.rootlayout);
        animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);

        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.stripe_anim);
        LinearLayout stripes = (LinearLayout) findViewById(R.id.stripes);

        stripes.startAnimation(anim);
       // anim.setDuration(2000);
       // anim.startNow();
    }


    public void enter(View view) {


        //check for id name == javier


        Intent intent = new Intent(this, maptrack.class);

        startActivity(intent);

    }

    public void signup(View view) {

        // Intent intent = new Intent(this, memberorexec.class);

        //  startActivity(intent);

    }


}
