package com.example.bloodhero;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class add_blood_req extends AppCompatActivity {
    TextView datePicker  , timePicker ;
    DatePickerDialog datePickerDialog ;
    String amPmChecker  , date  ="null", time ="null", Loc  ;

    Button apos ,amin , bpos , bmin , opos , omin , abpos , abmin  , backBtn;
    String bldGroup = "bloodGroup" ;
    Button submitBtn ;
    EditText needer   ,  loc  ;
    String  ph =  "nan" ;
    String pp = " ";

    DatabaseReference mRef ;
    ProgressBar mbar ;

    String uid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_req);
        mRef = FirebaseDatabase.getInstance().getReference("bloodReqRepo");
        apos = findViewById(R.id.apos);
        amin = findViewById(R.id.bldGroupIdAmin);
        bpos = findViewById(R.id.bldGroupIdBpos);
        bmin = findViewById(R.id.bldGroupIdBmin);
        omin = findViewById(R.id.bldGroupIdOmin);
        opos = findViewById(R.id.bldGroupIdOpos);
        abmin = findViewById(R.id.bldGroupIdABmin);
        abpos = findViewById(R.id.bldGroupIdABpos);
        submitBtn = findViewById(R.id.submitBtn);
        timePicker = findViewById(R.id.timeEdit);
        datePicker = findViewById(R.id.dateEdit);
        needer = findViewById(R.id.name_add);
        loc = findViewById(R.id.loc);
        mbar = findViewById(R.id.progresssbar);
        mbar.setVisibility(View.GONE);
        backBtn = findViewById(R.id.backBtnAdd);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //----------------------------->

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


        //<-----------------




        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mbar.setVisibility(View.VISIBLE);

                String Name = needer.getText().toString();
                String LOc = loc.getText().toString();


                if(!TextUtils.isEmpty(Name) && !TextUtils.isEmpty(LOc)&& !bldGroup.contains("bloodGroup")
                ){


                    uploadDataToFireBase(Name , LOc   , ph , time , date );

                    //   OpenDialogue();

                    Toast.makeText(getApplicationContext() , Name + "" + LOc , Toast.LENGTH_SHORT )
                            .show();

                }
                else {
                    Toast.makeText(getApplicationContext() , "Fill the Data Properly" , Toast.LENGTH_SHORT)
                            .show();
                    mbar.setVisibility(View.GONE);
                }



            }
        });



        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calling the date Picker

                Calendar c = Calendar.getInstance();

                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(add_blood_req.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        datePicker.setText(dayOfMonth + "/"+(month+1)+"/"+year);
                        date = dayOfMonth + "/"+(month+1)+"/"+year ;



                    }
                } ,year , month , day);

                datePickerDialog.show();
            }
        });



        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calling the time picker

                TimePickerDialog timePickerDialog  = new TimePickerDialog(add_blood_req.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay >= 12){
                            amPmChecker = "AM";

                        }
                        else  {
                            amPmChecker= "PM";
                        }


                        timePicker.setText(hourOfDay+":"+minute+" "+amPmChecker);

                        time = hourOfDay+":"+minute+" "+amPmChecker ;

                    }
                }, 0,0, false);


                timePickerDialog.show();
            }
        });

    }

    private void uploadDataToFireBase(String name, String lOc, String ph, String time, String date) {

        String id  = mRef.push().getKey();

        /// String postID , uid , needer  , loc , timee , datee , bg ;

        modelForBloodReq model = new modelForBloodReq(id  , uid , name , lOc  , time , date   , bldGroup, ph  , pp  ) ;

        mRef.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                mbar.setVisibility(View.GONE);

                OpenDialogue();



            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Toast.makeText(getApplicationContext() , "Error :"+ e.getMessage() , Toast.LENGTH_SHORT)
                                .show();
                        mbar.setVisibility(View.GONE);
                    }
                });
    }

    private void OpenDialogue() {

        final Dialog dialog  = new Dialog(add_blood_req.this);
        dialog.setContentView(R.layout.done_dialogue_in_blood) ;

        Button okBtn = dialog.findViewById(R.id.okBtn) ;


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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mauth  = FirebaseAuth.getInstance();
        uid = mauth.getUid() ;


        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                getProfile model = dataSnapshot.getValue(getProfile.class);

                ph = model.getMail();
                pp = model.getUser_pp() ;




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
