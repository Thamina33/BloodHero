package com.example.bloodhero.DonorPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.bloodhero.R;

public class ListActivity extends AppCompatActivity {

    String state ;
    RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent i = getIntent();
        state = i.getStringExtra("state") ;

        recyclerView = findViewById(R.id.LIst) ;


        // category this view  ......











    }
}
