package com.example.bloodhero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            Intent i = new Intent(getApplicationContext() , LogInPage.class);
            startActivity(i);
            finish();

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
                o = new Intent(getApplicationContext() ,LogInPage.class );
                startActivity(o);
            }
        });



    }
}
