package com.example.bloodhero.DonorPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bloodhero.R;
import com.example.bloodhero.loginChooser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class donorDashBoard extends AppCompatActivity {

    String  headerName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_dash_board);
        Button pendingBtn = findViewById(R.id.checkPending);
        Button checkAcceptBtn = findViewById(R.id.checkAccpt);
        Button logOut = findViewById(R.id.logout);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("username" , "No name defined") ;
                editor.putString("password", "No name defined");
                editor.putString("state","No name defined") ;
                editor.apply();
                editor.commit();

                Intent i  = new Intent( getApplicationContext() , loginChooser.class) ;
                startActivity(i);


            }
        }) ;



        pendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final     Intent i = new Intent(getApplicationContext() , ListActivity.class);
                i.putExtra("state", "pending");

                startActivity(i);

            }
        });

        checkAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext() , onGoingDontaion.class);
                ii.putExtra("state", "accept");


                startActivity(ii);

            }
        }) ;





    }

    @Override
    protected void onStart() {
        super.onStart();
        // create a dialouge to load

        final ProgressDialog dialog = new ProgressDialog(donorDashBoard.this);
        dialog.setTitle("Please Wait..");
        dialog.setMessage("While We Check The Boring Thing!!!");
        dialog.setCancelable(false);
        dialog.show();

        // check the profile is active or not


        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        final String USERNAME = prefs.getString("username", "No name defined");//"No name defined" is the default value.
        final String PASS = prefs.getString("password", "No name defined");


        // 1st check for user  available

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(USERNAME+PASS).child("status");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {

                       String  status = dataSnapshot.getValue().toString() ;

                       if ( status.equals("active"))
                       {
                           dialog.dismiss();

                            DatabaseReference reff = FirebaseDatabase.getInstance().getReference("users").child(USERNAME+PASS)
                                    .child("co_ordName");

                           reff.addListenerForSingleValueEvent(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                   SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                   SharedPreferences.Editor editor = pref.edit();
                                   editor.putString("header",  dataSnapshot.getValue().toString()) ;
                                   editor.apply();
                                   editor.commit();


                               }

                               @Override
                               public void onCancelled(@NonNull DatabaseError databaseError) {

                               }
                           });

                            dialog.dismiss();

                       }
                       else {
                           dialog.dismiss();
                           Intent i = new Intent(getApplicationContext() , pendingPageForDonor.class);
                           startActivity(i);
                           finish();


                       }

                }
                else {

                    dialog.dismiss();

                    finish();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


                dialog.dismiss();
            }
        });






    }
}
