package com.example.bloodhero;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


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

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


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


        //selectinng blood

        apos.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                bldGroup = "A+";

                apos.setBackground(getDrawable(R.drawable.white_btn_pink_border));
                apos.setTextColor(Color.parseColor("#FF245C"));


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
                amin.setTextColor(Color.parseColor("#FF245C"));


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
                bpos.setTextColor(Color.parseColor("#FF245C"));


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
                bmin.setTextColor(Color.parseColor("#FF245C"));


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
                opos.setTextColor(Color.parseColor("#FF245C"));


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
                omin.setTextColor(Color.parseColor("#FF245C"));


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
                abpos.setTextColor(Color.parseColor("#FF245C"));


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
                abmin.setTextColor(Color.parseColor("#FF245C"));


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

                String Name = dname.getText().toString();
                String Email = dEmail.getText().toString();

                if(!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(Email)&& !bldGroup.contains("bloodGroup") && !gndr.contains("gender"))
                {


                    uploadDataToFireBase(Name , Email   , gndr , bldGroup , numVisibility );

                }
                else {
                    Toast.makeText(getApplicationContext() , "Fill the Data Properly" , Toast.LENGTH_SHORT)
                            .show();

                }



            }
        });

    }

    public void uploadDataToFireBase(String name, String email, String gndr, String bldGroup, String numVisibility) {

        String id  = mRef.push().getKey();

        DonorInformation model = new DonorInformation(id  , Uid , name , email  , gndr ,  bldGroup, numVisibility  ) ;

        mRef.child(id).setValue(model);
        Toast.makeText(getApplicationContext() , "Success" , Toast.LENGTH_SHORT)
                .show();
    }
}