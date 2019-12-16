package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();


        Handler  handler = new Handler();


            handler.postDelayed(new Runnable() {
                @Override
                public void run() {



                        Intent i = new Intent(getApplicationContext() , loginChooser.class);
                        startActivity(i);
                        finish();




                }
            } , 500) ;





    }
}
