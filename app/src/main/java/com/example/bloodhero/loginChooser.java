package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bloodhero.DonorPackage.donorDashBoard;
import com.example.bloodhero.DonorPackage.loginPageForDonor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginChooser extends AppCompatActivity {
    Intent o  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_chooser);
        final  Button userbtn = findViewById(R.id.user);
        final Button donor = findViewById(R.id.donor);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if (user != null)
        {

            Intent i = new Intent(getApplicationContext() , HomePage.class);
            startActivity(i);
            finish();

        }
        else {


        }


        userbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                o = new Intent(getApplicationContext() ,LogInPage.class );
                startActivity(o);

            }
        }) ;


        donor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o = new Intent(getApplicationContext() , loginPageForDonor.class );
                startActivity(o);
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();


        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String   USERNAME  = prefs.getString("username", "No name defined");//"No name defined" is the default value.
        String PASS = prefs.getString("password", "No name defined");

        if(USERNAME.equals("No name defined") || PASS.equals("No name defined"))
        {

            o = new Intent(getApplicationContext() , donorDashBoard.class );
                startActivity(o);
        }
    }
}
