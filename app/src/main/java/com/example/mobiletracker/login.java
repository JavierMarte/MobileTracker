package com.example.mobiletracker;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class login extends AppCompatActivity {

    private ConstraintLayout rootLayout;
    private AnimationDrawable animDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        View decorView = getWindow().getDecorView();
//flag for making android layout a full screan
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        rootLayout = (ConstraintLayout) findViewById(R.id.rootlayout);
        animDrawable = (AnimationDrawable) rootLayout.getBackground();
        animDrawable.setEnterFadeDuration(10);

        animDrawable.setExitFadeDuration(5000);
        animDrawable.start();

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.stripe_anim);
        LinearLayout stripes = (LinearLayout) findViewById(R.id.stripes);

        stripes.startAnimation(anim);

    }


    public void enter(View view) {



        Firebase.setAndroidContext(this);

        Firebase myFirebaseRef = new Firebase("https://mobiletracker-d4f90.firebaseio.com/");




        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EditText usernametxt = (EditText) findViewById(R.id.username);
                EditText passwordtxt = (EditText) findViewById(R.id.password);


                System.out.println(dataSnapshot.child(usernametxt.getText().toString()).getValue());


                if(dataSnapshot.child(usernametxt.getText().toString()).exists()){

                   DataSnapshot tempuser =  dataSnapshot.child(usernametxt.getText().toString());
                    if(tempuser.getValue().equals(passwordtxt.getText().toString())){
                        Intent intent = new Intent(login.this, maptrack.class);

                        startActivity(intent);

                    }

                }else{

                    //doesn't exist
                    Toast.makeText(getApplicationContext(),"doesn't exist", Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

                Toast.makeText(getApplicationContext(),"error uploading to server!!", Toast.LENGTH_SHORT).show();

            }


        });





    }



}
