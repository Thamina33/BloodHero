package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    CircleImageView profilee     ;
    TextView name, ph , bg ,  id;

    Button back;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        name = findViewById( R.id.name) ;
        ph = findViewById(R.id.ph) ;
        back = findViewById(R.id.BACKBTN);
        bg = findViewById(R.id.blood_profile) ;
        profilee = findViewById(R.id.pic) ;
        String uid = FirebaseAuth.getInstance().getUid() ;



        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("DonorInformation").child(uid);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });
        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                getProfile profile = dataSnapshot.getValue(getProfile.class);

                bg.setText(profile.getBloodgroup());
                name.setText(profile.getName());
                ph.setText(profile.getMail());
                Glide.with(getApplicationContext()) .load(profile.getImageLink()).diskCacheStrategy(DiskCacheStrategy.ALL).into(profilee) ;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
