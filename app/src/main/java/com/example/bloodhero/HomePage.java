package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
    Button findDonorBtn , addReqBtn  , showReq, myreq  ;
    TextView donornumber , ownReq , sameDonorNumber , reqnumber  ;
    FirebaseAuth mauth ;
    String uid ;
    ImageView profile , logout ;

    int count = 0 ;

    Intent i  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();


        //init the view
        donornumber = findViewById(R.id.donorNumber);
        ownReq = findViewById(R.id.ownReqNum);
        sameDonorNumber = findViewById(R.id.sameReqList);
        reqnumber = findViewById(R.id.reqNumber);
        profile = findViewById(R.id.profile_image);
        logout = findViewById(R.id.logOUT);

        findDonorBtn = findViewById(R.id.findDonorBtn);
        addReqBtn = findViewById(R.id.addReq_btn);
        showReq = findViewById(R.id.seeReqBtn);
        myreq = findViewById(R.id.myReqBtn);


        //setON Click listeners


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  n = new Intent(getApplicationContext() , Profile.class);
                startActivity(n);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               FirebaseAuth mauth  =FirebaseAuth.getInstance() ;
               mauth.signOut();
               Intent  n = new Intent(getApplicationContext() , LogInPage.class);
               startActivity(n);
                finish();
            }
        });

        addReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                i = new Intent(getApplicationContext() ,add_blood_req.class );
                startActivity(i);
            }
        });

        findDonorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext() ,chooseBloodGroup.class );
                startActivity(i);


            }
        });

        showReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext() ,requestedBloodList.class );
                startActivity(i);



            }
        });


        myreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext() ,myReq.class );
                startActivity(i);



            }
        });


        getTotalcountOFUsers();
        getTotalReq();
        getOwnReq();
    }

    @Override
    protected void onStart() {
        super.onStart();
            super.onStart();
            FirebaseAuth mAuth ;
            FirebaseUser mUser ;
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();

            if(mUser == null){

                Intent i = new Intent(getApplicationContext(), LogInPage.class);
                startActivity(i);
                finish();
            }

        }


    public  void getTotalcountOFUsers(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

// i used the single or the value.. depending if you want to keep track
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");

                if(dataSnapshot.getKey().equals("DonorInformation")){


                    donornumber.setText(  dataSnapshot.getChildrenCount()+ "");

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public  void getTotalReq(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

// i used the single or the value.. depending if you want to keep track
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Log.e(dataSnapshot.getKey(),dataSnapshot.getChildrenCount() + "");

                if(dataSnapshot.getKey().equals("bloodReqRepo")){


                    reqnumber.setText(  dataSnapshot.getChildrenCount()+ "");
                    sameDonorNumber.setText(dataSnapshot.getChildrenCount()+"");

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
    public  void getOwnReq(){

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("bloodReqRepo");
        Query query = myRef.orderByChild("uid").equalTo(uid);


// i used the single or the value.. depending if you want to keep track

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                count = (int) dataSnapshot.getChildrenCount();
                ownReq.setText(count + "");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

}
