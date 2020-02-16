package com.example.mobiletracker;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class loginmenu extends AppCompatActivity {
    private ConstraintLayout rootLayout;
    private AnimationDrawable animDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginmenu);

        View decorView = getWindow().getDecorView();
//flag for making android layout a full screan
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

//gets background id and sets it to an animated video
        rootLayout = (ConstraintLayout) findViewById(R.id.rootlayout);
        animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);

        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.stripe_anim);
        LinearLayout stripes = (LinearLayout) findViewById(R.id.stripes);

        stripes.startAnimation(anim);
    }

    //goes into next window which is login screan.
    public void login(View view) {

        Intent intent = new Intent(this, login.class);

        startActivity(intent);



    }
    //goes into next window which is signup screan.
    public void signup(View view) {

         Intent intent = new Intent(this, signup.class);

          startActivity(intent);

    }


}
