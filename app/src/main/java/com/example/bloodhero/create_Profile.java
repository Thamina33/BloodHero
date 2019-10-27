package com.example.bloodhero;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class create_Profile extends AppCompatActivity {

    EditText dname, dEmail;
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
    String uid ;
    CircleImageView imageView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile);

        mRef = FirebaseDatabase.getInstance().getReference("DonorInformation");

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid() ;
//        String email  = mauth.getCurrentUser().getEmail() ;

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


     //   dEmail.setText(email);
        //selectinng blood

        apos.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "A+";

                apos.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                apos.setTextColor(Color.parseColor("#FFF"));


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
                amin.setTextColor(Color.parseColor("#FFF"));


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
                bpos.setTextColor(Color.parseColor("#FFF"));


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
                bmin.setTextColor(Color.parseColor("#FFF"));

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
                opos.setTextColor(Color.parseColor("#FFF"));


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
                omin.setTextColor(Color.parseColor("#FFF"));


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
                abpos.setTextColor(Color.parseColor("#FFF"));


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
                abmin.setTextColor(Color.parseColor("#FFF"));


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
                m_txt.setTextColor(Color.parseColor("#FF245C"));


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
                f_txt.setTextColor(Color.parseColor("#FF245C"));


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
                    Toast.makeText(getApplicationContext(), Name + "" + Email + "" + bldGroup, Toast.LENGTH_SHORT)
                            .show();

                } else {
                    Toast.makeText(getApplicationContext(), "Fill the Data Properly", Toast.LENGTH_SHORT)
                            .show();
                    mbar.setVisibility(View.GONE);

                }


            }
        });

    }

    public void uploadDataToFireBase(String name, String email, String gndr, String bldGroup, String numVisibility) {

        String id = mRef.push().getKey();

        getProfile model = new getProfile(id, uid, name, email, gndr, bldGroup, numVisibility , "TRUE" , null);

        mRef.child(uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                mbar.setVisibility(View.INVISIBLE);

                Intent intent = new Intent(create_Profile.this,HomePage.class);
                startActivity(intent);
                finish();
             //   OpenDialogue();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
                mbar.setVisibility(View.GONE);
            }
        });
    }


    private void OpenDialogue() {

        final Dialog dialog = new Dialog(create_Profile.this);
        dialog.setContentView(R.layout.done_dialogue_in_profile);

        Button okBtn = dialog.findViewById(R.id.okBtn);


        dialog.setCancelable(false);

        dialog.show();


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                finish();
            }
        });


    }


}


