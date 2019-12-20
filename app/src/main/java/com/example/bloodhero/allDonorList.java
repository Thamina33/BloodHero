package com.example.bloodhero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class allDonorList extends AppCompatActivity {
    LinearLayoutManager mlayoutManager;
    RecyclerView mrecyclerView;
    FirebaseDatabase mFirebaseDatabase ;
    DatabaseReference mRef , likeRef;
    FirebaseAuth mauth ;

    String uid  ;
    String searchText ;
    Button backbtn ;
    public FirebaseRecyclerAdapter<getProfile, viewHolderForAllDonorList> firebaseRecyclerAdapter ;
    public FirebaseRecyclerOptions<getProfile> options ; // seraching in the profile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donor_list);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }



        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("DonorInformation");
        backbtn = findViewById(R.id.BACKBTN);
        mRef.keepSynced(true);


        Intent i = getIntent();

        searchText = i.getStringExtra("BG");

        //calling RecyclerView
        mrecyclerView = findViewById(R.id.reclyclerViewForAllDonorList);
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        showData();


    }
    private  void showData(){


        Query query = mRef.orderByChild("bloodgroup").startAt(searchText).endAt(searchText + "\uf8ff") ;

        options = new FirebaseRecyclerOptions.Builder<getProfile>().setQuery(query , getProfile.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<getProfile, viewHolderForAllDonorList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderForAllDonorList holder, final int position, @NonNull getProfile model) {

                //  holder.setIsRecyclable(false); // dunno why if it crash then remove it

                holder.SetDeatils(getApplicationContext(), model.getName(), model.getGender(),
                        model.getBloodgroup(), model.getMail(), model.getImageLink());





// love button function
                holder.triigerDiagoue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // trigger thet dialgoue here

                        String number = "+88"+ getItem(position).getMail();

                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
                        startActivity(intent);


                    }
                });


            }

            @NonNull
            @Override
            public viewHolderForAllDonorList onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_all_donor, viewGroup, false);

                viewHolderForAllDonorList viewHolder = new viewHolderForAllDonorList(itemVIew);





                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mlayoutManager);

        firebaseRecyclerAdapter.startListening();

        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
