package com.example.bloodhero.DonorPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodhero.R;
import com.example.bloodhero.getProfile;
import com.example.bloodhero.modelForBloodReq;
import com.example.bloodhero.viewHolderForAllDonorList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ListActivity extends AppCompatActivity {

    String state ;
    String header , USERNAME   , PASS   ;
    RecyclerView mrecyclerView ;
    LinearLayoutManager mlayoutManager ;

    public FirebaseRecyclerAdapter<modelForpedingReqForDonor, viewHolderForPendingReqForDonor> firebaseRecyclerAdapter ;
    public FirebaseRecyclerOptions<modelForpedingReqForDonor> options ; // seraching in the profile ;
    DatabaseReference mref ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        state = i.getStringExtra("state") ;

        mrecyclerView = findViewById(R.id.LIst) ;
        mrecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout
        mlayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mlayoutManager.setReverseLayout(true);
        mlayoutManager.setStackFromEnd(true);






    }
    private  void showData(String header){

        mref = FirebaseDatabase.getInstance().getReference("users").child(header).child("pendingReq");
        Query query = mref.orderByChild("status").equalTo("pending") ;

        options = new FirebaseRecyclerOptions.Builder<modelForpedingReqForDonor>().setQuery(query , modelForpedingReqForDonor.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForpedingReqForDonor, viewHolderForPendingReqForDonor>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewHolderForPendingReqForDonor holder, final int position, @NonNull modelForpedingReqForDonor model) {

                //  holder.setIsRecyclable(false); // dunno why if it crash then remove it

                holder.setDetails( model.getPostID(), model.getDate());





// love button function

                holder.chckBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Trigger the dialogue Here !!!

                        triggerDialogueONcomplete(getItem(holder.getAdapterPosition()).getPostID());

                    }
                });



            }

            @NonNull
            @Override
            public viewHolderForPendingReqForDonor onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row

                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_for_donation_pending, viewGroup, false);

                viewHolderForPendingReqForDonor viewHolder = new viewHolderForPendingReqForDonor(itemVIew);





                return viewHolder;
            }
        };

        mrecyclerView.setLayoutManager(mlayoutManager);

        firebaseRecyclerAdapter.startListening();

        //setting adapter

        mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }



    public void triggerDialogueONcomplete(final String id ) {

        // creating a dialogue box
        final Dialog DoneDailogue = new Dialog(ListActivity.this);
        DoneDailogue.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DoneDailogue.setContentView(R.layout.row_for_pending);

     //   Button okBtn = (Button) DoneDailogue.findViewById(R.id.ok_Button);
     final    Button acceptBtn , rejectBtn , bldBtn ;
        final TextView pateintName , pateintNumber , pateientAdress , dateTv ;

        pateintName = (TextView) DoneDailogue.findViewById(R.id.patientName);
        pateientAdress =(TextView)DoneDailogue.findViewById(R.id.adress) ;
        pateintNumber = (TextView) DoneDailogue.findViewById(R.id.pateientNumber);
        dateTv = DoneDailogue.findViewById(R.id.time) ;
        bldBtn = DoneDailogue.findViewById(R.id.requestedBlood);
        acceptBtn= DoneDailogue.findViewById(R.id.acceptBtn);
        rejectBtn = DoneDailogue.findViewById(R.id.rejectBtn);

        // download the data from the server  .
        final DatabaseReference mref = FirebaseDatabase.getInstance().getReference("bloodReqRepo").child(id);

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelForBloodReq model = dataSnapshot.getValue(modelForBloodReq.class);

                pateintName.setText(model.getNeeder());
                pateientAdress.setText(model.getLoc()) ;
                pateintNumber.setText(model.getPhone()) ;
                dateTv.setText(model.getStatus()) ;
                bldBtn.setText(model.getBg());

                acceptBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // update the status of the repo

                        // take the header  name also

                        DatabaseReference reqRepo = FirebaseDatabase.getInstance().getReference("bloodReqRepo").child(id);

                        DatabaseReference myself = FirebaseDatabase.getInstance().getReference("users")
                                .child(USERNAME+PASS).child("pendingReq").child(id).child("status");

                        DatabaseReference  headerRef = FirebaseDatabase.getInstance().getReference("users")
                                .child(header).child("pendingReq").child(id).child("status") ;

                        //  remove the testtest with  blood donor UID ;

                        myself.setValue(USERNAME+PASS+ "ONGOING") ;

                        headerRef.setValue(USERNAME+PASS + "ONGOING") ;

                        reqRepo.child("time").setValue("ReqAccepted").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                // saving the post ID .....
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("postId",id) ;
                                editor.apply();
                                editor.commit();

                                // send it to the next activity

                                Intent intent = new Intent(getApplicationContext() , onGoingDontaion.class);
                                startActivity(intent);




                            }
                        }) ;



                    }
                }) ;

                rejectBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // notify the head that  it was rejected


                    }
                });






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        DoneDailogue.setCancelable(false);
        DoneDailogue.show();

    }


    @Override
    protected void onStart() {
        super.onStart();


        final ProgressDialog dialog = new ProgressDialog(ListActivity.this);
        dialog.setTitle("loading");
        dialog.show();

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        header = prefs.getString("header", "No name defined");
          USERNAME = prefs.getString("username", "No name defined");//"No name defined" is the default value.
          PASS = prefs.getString("password", "No name defined");
        if(header.equals("No name defined"))
        {


        }
        else {

            DatabaseReference mref = FirebaseDatabase.getInstance().getReference("login").child("co-ordinator")
                    .child(header).child("pass");

            mref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    header = header + dataSnapshot.getValue().toString() ;

                    Toast.makeText(getApplicationContext() , header , Toast.LENGTH_SHORT ).show();

                    dialog.dismiss();
                    showData(header);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }






    }
}
