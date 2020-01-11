package com.example.bloodhero.DonorPackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodhero.R;
import com.example.bloodhero.modelForBloodReq;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class onGoingDontaion extends AppCompatActivity {
    TextView nameET , bgET , areaET  , phoneET , timeET ;
    private Bitmap compressedImageFile;
    Button accptBtn ;
    DatabaseReference myself ;
    Uri mFilePathUri ;
    String ID , USERNAME , PASS;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_going_dontaion);

        // gett the  running  post id




        nameET =findViewById(R.id.name);
        bgET =findViewById(R.id.bg);
        areaET =findViewById(R.id.area);
        phoneET =findViewById(R.id.ph);
        timeET =findViewById(R.id.time);
        accptBtn = findViewById(R.id.acceptBtn) ;

        Button home = findViewById(R.id.homeBtn);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent io = new Intent(getApplicationContext() , donorDashBoard.class);
                startActivity(io);

            }
        });



        progressDialog = new ProgressDialog(onGoingDontaion.this);
        progressDialog.setTitle("Uploading The Image.....");
        progressDialog.setCancelable(false);

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        USERNAME  = prefs.getString("username", "No name defined");//"No name defined" is the default value.
        PASS = prefs.getString("password", "No name defined");
        ID  = prefs.getString("postId", "No name defined");
        myself = FirebaseDatabase.getInstance().getReference("users").child(USERNAME + PASS).child("pendingReq").child(ID).child("postID");


        if(ID.equals("No name defined"))
        {
            Toast.makeText(getApplicationContext() ,"Error : " , Toast.LENGTH_SHORT )
               .show();
        }
        else if (ID.equals("null"))
        {


            AlertDialog dialog = new AlertDialog.Builder(onGoingDontaion.this).create();
            dialog.setTitle("There Is No OnGoing Donation Set For You");
            dialog.setMessage("Please Go Back");
            dialog.setButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();

                }
            });

        }

        else {
      //  Toast.makeText(getApplicationContext() ,ID , Toast.LENGTH_SHORT )
             //   .show();
            loadDatainForm(ID) ;


        }


            accptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if (ContextCompat.checkSelfPermission(onGoingDontaion.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(onGoingDontaion.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                            BringImagePicker();



                        } else {

                            BringImagePicker();

                        }

                    } else {

                        BringImagePicker();

                    }

                }
            });
    }

    private void loadDatainForm(String ID) {



                    DatabaseReference reqREF = FirebaseDatabase.getInstance().getReference("bloodReqRepo")
                            .child(ID);

                    reqREF.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot Snapshot) {


                            modelForBloodReq  reqModel =  Snapshot.getValue(modelForBloodReq.class) ;


                            nameET.setText(reqModel.getNeeder()) ;
                            phoneET.setText(reqModel.getPhone());
                            areaET.setText(reqModel.getLoc());
                            bgET.setText(reqModel.getBg());
                            timeET.setText(reqModel.getTimee()+ " " + reqModel.getDatee());




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });






        
    }
    private void BringImagePicker () {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .setCropShape(CropImageView.CropShape.RECTANGLE) //shaping the image
                .start(onGoingDontaion.this);

    }

    @Override
    protected void onActivityResult(/*int requestCode, int resultCode, @Nullable Intent data*/
            int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);



        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mFilePathUri = result.getUri();
               // profileImage.setImageURI(mFilePathUri);
                if(mFilePathUri == null)
                {
                    Toast.makeText(this, "Please Add an image",Toast.LENGTH_LONG).show();


                }
                else {


                   uploadImageToPostReq() ;


                }





            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }

    private void uploadImageToPostReq()
    {



        final DatabaseReference reqREF = FirebaseDatabase.getInstance().getReference("bloodReqRepo")
            .child(ID);


        // PHOTO UPLOAD
        File newImageFile = new File(mFilePathUri.getPath());

        try {

            compressedImageFile = new Compressor(onGoingDontaion.this)
                    .setMaxHeight(920)
                    .setMaxWidth(920)
                    .setQuality(40)
                    .compressToBitmap(newImageFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

            progressDialog.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] imageData = baos.toByteArray();
        StorageReference mStorage = FirebaseStorage.getInstance().getReference("completedDonationPic");
        UploadTask filePath = mStorage.child(ID + ".jpg").putBytes(imageData);
        filePath.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isSuccessful()) ;
                Uri downloaduri = uriTask.getResult();


                reqREF.child("status").setValue("DONE") ;
                reqREF.child("imageLink").setValue(downloaduri.toString()).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("postId","No name defined") ;
                        editor.apply();
                        editor.commit();

                        //TODO TRIGGER NOTTIFICATIONS TO HEAD and requested user

                        progressDialog.dismiss();
                        updateTheCounter() ;

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })  ;



            }
        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // hide progrees bar
                     //   mprogressDialog.dismiss();
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress( @NonNull  UploadTask.TaskSnapshot taskSnapshot) {


                progressDialog.setTitle(" Uploading ...........");
            }
        });




    }

    private void updateTheCounter() {
    final     DatabaseReference mre = FirebaseDatabase.getInstance().getReference("counter").child("totalCount");

        mre.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int counter = Integer.parseInt(dataSnapshot.getValue().toString())  ;


                // update the counter

                mre.setValue(String.valueOf(counter+1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("postId","null") ;
                        editor.apply();
                        editor.commit();
                        Intent io = new Intent(getApplicationContext() , donorDashBoard.class);
                        startActivity(io);
                        finish();

                    }
                }) ;



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent io = new Intent(getApplicationContext() , donorDashBoard.class);
        startActivity(io);


    }
}
