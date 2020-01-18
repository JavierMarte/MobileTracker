package com.example.mobiletracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }


    public void login(View view) {

        Intent intent = new Intent(this, signup.class);

        startActivity(intent);

    }

    public void signup(View view) {

        // Intent intent = new Intent(this, memberorexec.class);

        //  startActivity(intent);

    }


}
