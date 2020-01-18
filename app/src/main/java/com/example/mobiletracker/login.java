package com.example.mobiletracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }


    public void enter(View view) {

        Intent intent = new Intent(this, maptrack.class);

        startActivity(intent);

    }

    public void signup(View view) {

        // Intent intent = new Intent(this, memberorexec.class);

        //  startActivity(intent);

    }


}
