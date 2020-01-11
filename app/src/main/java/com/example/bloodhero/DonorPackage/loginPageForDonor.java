package com.example.bloodhero.DonorPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.bloodhero.R;
import com.example.bloodhero.create_Profile;
import com.example.bloodhero.getProfile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginPageForDonor extends AppCompatActivity {
    String db = null ;
    EditText user  , pas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_for_donor);
        final Button preceedBtn = findViewById(R.id.proceedBtn);
        final Button regBtn = findViewById(R.id.regBtn) ;
        user = findViewById(R.id.usernameEt);
        pas = findViewById(R.id.passEt);


     preceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(user.getText().toString() , pas.getText().toString() , "users");
            }
        });

     regBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent i = new Intent(getApplicationContext() , create_Profile.class );
             startActivity(i);


         }
     });




    }
    private void loginUser(final String userName, final String pass, final String db) {


        // TODO for  when u pick to do this just make sure this  top send the user to the dash board
        final  DatabaseReference mreff = FirebaseDatabase.getInstance().getReference().child(db).child(userName+pass);

        mreff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    try{
                        mreff.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                getProfile model = dataSnapshot.getValue(getProfile.class);

                                if(userName.equals(model.getUsername() )&& pass.equals(model.getPass()))
                                {
                                    // user is authenticated
                                 //   Toast.makeText(getApplicationContext(), "Success!!", Toast.LENGTH_SHORT).show();

                                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("username" , userName) ;
                                    editor.putString("password", pass);
                                    editor.putString("state","donor") ;
                                    editor.apply();
                                    editor.commit();





                                    Intent o = new Intent(getApplicationContext()  , donorDashBoard.class);
                                    startActivity(o);




                                }
                                else
                                {
                                    Toast.makeText(loginPageForDonor.this, "Username/Password Was Wrong", Toast.LENGTH_SHORT).show();


                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    catch ( Exception e )
                    {
                        Toast.makeText(loginPageForDonor.this, "Username/Password Was Wrong", Toast.LENGTH_SHORT).show();


                    }
                }
                else {
                    Toast.makeText(loginPageForDonor.this, "Username/Password Was Wrong", Toast.LENGTH_SHORT).show();


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();


        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String USERNAME = prefs.getString("username", "No name defined");
        String PASS = prefs.getString("password", "No name defined");


        if( !USERNAME.equals("No name defined") && !PASS.equals("No name defined") )
        {

            Intent o = new Intent(getApplicationContext()  , donorDashBoard.class);
            startActivity(o);


        }


    }

}

