package com.example.bloodhero;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;

public class create_Profile extends AppCompatActivity {

    EditText dname, dEmail;
    Uri mFilePathUri ;
    private Bitmap compressedImageFile;
    LinearLayout gmale, gfemale;
    ImageView m_icon, f_icon;
    TextView m_txt, f_txt;
    Button apos, amin, bpos, bmin, abpos, abmin, opos, omin, submit;
    CheckBox num_vgblity_prmisn;
    String name, mail;
    String bldGroup = "bloodGroup";
    String gndr = "gender";
    String numVisibility = "visible";
    String Uid;
    DatabaseReference mRef;
    ProgressBar mbar;
    FirebaseAuth mauth ;
    StorageReference mStorageReference ;
    String uid ;
    CircleImageView imageView ;
    RelativeLayout  loader ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile);

        mRef = FirebaseDatabase.getInstance().getReference("DonorInformation");
        mStorageReference = FirebaseStorage.getInstance().getReference("profilePic") ;
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid() ;
//        String email  = mauth.getCurrentUser().getEmail() ;
        loader = findViewById(R.id.loadingPanel) ;
        dname = findViewById(R.id.dName);
        dEmail = findViewById(R.id.dEmail);
        gmale = findViewById(R.id.gmale);
        gfemale = findViewById(R.id.gfemale);
        m_icon = findViewById(R.id.m_icon);
        f_icon = findViewById(R.id.f_icon);
        f_txt = findViewById(R.id.f_txt);
        m_txt = findViewById(R.id.m_txt);
        apos = findViewById(R.id.apos);
        amin = findViewById(R.id.amin);
        bmin = findViewById(R.id.bmin);
        omin = findViewById(R.id.omin);
        abmin = findViewById(R.id.abmin);
        bpos = findViewById(R.id.bpos);
        opos = findViewById(R.id.opos);
        abpos = findViewById(R.id.abpos);
        submit = findViewById(R.id.submit);
        num_vgblity_prmisn = findViewById(R.id.num_vgblity_prmisn);
        mbar = findViewById(R.id.progresssbar);
        imageView = findViewById(R.id.profile_image) ;
        mbar.setVisibility(View.GONE);


        loader.setVisibility(View.GONE);

        FirebaseAuth  mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser() ;

        try{
            dEmail.setText(user.getPhoneNumber());

        }
        catch ( Exception e )
        {

            dEmail.setHint("Enter Your Number");


        }

     //   dEmail.setText(email);
        //selectinng blood

        apos.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "A+";

                apos.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                apos.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });

        amin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "A-";

                amin.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                amin.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                apos.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });


        bpos.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "B+";

                bpos.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                bpos.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                apos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });

        bmin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "B-";

                bmin.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                bmin.setTextColor(Color.parseColor("#FFFFFF"));

                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                apos.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });


        opos.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "O+";

                opos.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                opos.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                apos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });

        omin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                bldGroup = "O-";

                omin.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                omin.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                apos.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });

        abpos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                bldGroup = "AB+";

                abpos.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                abpos.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                abmin.setTextColor(Color.parseColor("#a2a2a2"));
                apos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });

        abmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bldGroup = "AB-";

                abmin.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                abmin.setTextColor(Color.parseColor("#FFFFFF"));


                //set all other button color  default

                amin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                bmin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                opos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                omin.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                apos.setBackground(getDrawable(R.drawable.white_btn_gray_border));
                abpos.setBackground(getDrawable(R.drawable.white_btn_gray_border));


                amin.setTextColor(Color.parseColor("#a2a2a2"));
                bpos.setTextColor(Color.parseColor("#a2a2a2"));
                bmin.setTextColor(Color.parseColor("#a2a2a2"));
                opos.setTextColor(Color.parseColor("#a2a2a2"));
                omin.setTextColor(Color.parseColor("#a2a2a2"));
                apos.setTextColor(Color.parseColor("#a2a2a2"));
                abpos.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });


        //selecting gender

        gmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gndr = "Male";

                m_icon.setBackground(getDrawable(R.drawable.pink_male));
                m_txt.setTextColor(Color.parseColor("#00652e"));


                //set female color default

                f_icon.setBackground(getDrawable(R.drawable.female));
                f_txt.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });

        gfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gndr = "Female";

                f_icon.setBackground(getDrawable(R.drawable.pink_female));
                f_txt.setTextColor(Color.parseColor("#00652e"));


                //set female color default

                m_icon.setBackground(getDrawable(R.drawable.male));
                m_txt.setTextColor(Color.parseColor("#a2a2a2"));

            }
        });


        num_vgblity_prmisn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num_vgblity_prmisn.isChecked()) {
                    numVisibility = "Yes";
                } else {
                    numVisibility = "No";
                }
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mbar.setVisibility(View.VISIBLE);
                String Name = dname.getText().toString();
                String Email = dEmail.getText().toString();


                if (!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Email) && !bldGroup.contains("bloodGroup") && !gndr.contains("gender")) {


                    uploadDataToFireBase(Name, Email, gndr, bldGroup, numVisibility);


                } else {
                    Toast.makeText(getApplicationContext(), "Fill the Data Properly", Toast.LENGTH_SHORT)
                            .show();
                    mbar.setVisibility(View.GONE);

                }


            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(create_Profile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(create_Profile.this,
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
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mFilePathUri = result.getUri();
                imageView.setImageURI(mFilePathUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }

    private void BringImagePicker() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1,1)
                .setCropShape(CropImageView.CropShape.OVAL) //shaping the image
                .start(create_Profile.this);


    }

    public void uploadDataToFireBase(final String name, final String email, final String gndr, final String bldGroup, final String numVisibility) {


        if(mFilePathUri != null)
        {

            final String randomName = UUID.randomUUID().toString();

            // PHOTO UPLOAD
            File newImageFile = new File(mFilePathUri.getPath());

            try {

                compressedImageFile = new Compressor(create_Profile.this)
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
            UploadTask filePath = mStorageReference.child(randomName+uid + ".jpg").putBytes(imageData);
            filePath.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                   // viewDialog.hideDialog();


                  //  viewDialog.hideDialog();

                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isSuccessful()) ;
                    Uri downloaduri = uriTask.getResult();



                    //  String ts =mDatabaseReference.push().getKey() ;
                    String id = mRef.push().getKey();

                    getProfile model = new getProfile(id, uid, name, email, gndr, bldGroup, numVisibility , "TRUE" , downloaduri.toString());

                    //   String imageUploadid = mDatabaseReference.push().getKey() ;

                    //adding imge upload
                    mRef.child(uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            loader.setVisibility(View.GONE);
                            sentToHome();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            loader.setVisibility(View.GONE);

                        }
                    })
                    ;



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // hide progrees bar
                    mbar.setVisibility(View.INVISIBLE);
                    loader.setVisibility(View.GONE);
                    //  mprogressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                 //   viewDialog.showDialog();
                    mbar.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.VISIBLE);

                    //      mprogressDialog.setTitle(" Uploading ...........");
                }
            });




        }
        else {
            mbar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please Select image or add image Name ", Toast.LENGTH_SHORT).show();
        }




        }

    private void sentToHome() {
        Intent intent = new Intent(create_Profile.this,HomePage.class);
        startActivity(intent);
        finish();

    }




}


