package com.example.bloodhero.DonorPackage;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bloodhero.LogInPage;
import com.example.bloodhero.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class uploadNidToTheServer extends AppCompatActivity {
    ProgressDialog progressDialog ;
    String DB  ;
    ImageView nidupload;
    Uri mFilePathUri ;
    DatabaseReference mref ;
    String name , pass ;

    StorageReference mStorageReference ;
    private Bitmap compressedImageFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_nid_to_the_server);
        nidupload= findViewById(R.id.nidUpload);
        final  Button nextStep = findViewById(R.id.nextStep);

        Intent p = getIntent();
             DB = p.getStringExtra("DB") ;
             name = p.getStringExtra("NAME") ;
             pass = p.getStringExtra("PASS") ;




        mStorageReference = FirebaseStorage.getInstance().getReference("nidDb");
        mref = FirebaseDatabase.getInstance().getReference("requestedUser").child("donor").child(DB);

        nidupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // carry with upload
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(uploadNidToTheServer.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(uploadNidToTheServer.this,
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

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mFilePathUri!= null)
                {
                    uploadDataToTHeFirebase();

                }
                else {

                    Toast.makeText(uploadNidToTheServer.this, "Please Upload your NID IMAGE !!",Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

    }

    private void BringImagePicker() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .setCropShape(CropImageView.CropShape.RECTANGLE) //shaping the image
                .start(uploadNidToTheServer.this);


    }
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mFilePathUri = result.getUri();
                nidupload.setImageURI(mFilePathUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }

    public  void  uploadDataToTHeFirebase()
    {

        if(mFilePathUri != null)
        {
            progressDialog = ProgressDialog
                    .show(uploadNidToTheServer.this, "Uploading", "PLease Wait !!", true, false);
            final String randomName = UUID.randomUUID().toString();

            // PHOTO UPLOAD
            File newImageFile = new File(mFilePathUri.getPath());

            try {

                compressedImageFile = new Compressor(this)
                        .setMaxHeight(920)
                        .setMaxWidth(920)
                        .setQuality(40)
                        .compressToBitmap(newImageFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 40, baos);
            byte[] imageData = baos.toByteArray();
            UploadTask filePath = mStorageReference.child(randomName+ ".jpg").putBytes(imageData);
            filePath.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {







                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    final Uri downloaduri = uriTask.getResult();

                //    mref.child("counter").setValue("00") ;
                    mref.child("id").setValue(downloaduri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        final DatabaseReference mRRef = FirebaseDatabase.getInstance().getReference("users").child(DB);
                        mRRef.child("id").setValue(downloaduri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                mRRef.child("counter").setValue("00") ;
                                // done With it
                                progressDialog.dismiss();
                                Intent oi = new Intent(getApplicationContext()  , loginPageForDonor.class) ;
                                startActivity( oi );
                                finish();
                            }
                        }) ;
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // hide progrees bar
               //     mbar.setVisibility(View.INVISIBLE);
                  //  loader.setVisibility(View.GONE);
                     progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override

                public void onProgress( @NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    //   viewDialog.showDialog();
                  //  mbar.setVisibility(View.VISIBLE);
                  //  loader.setVisibility(View.VISIBLE);

                          progressDialog.setTitle(" Uploading ......");
                }
            });




        }
        else {
            //mbar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please Select image or add image Name ", Toast.LENGTH_SHORT).show();
        }


    }
}
