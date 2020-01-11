package com.example.bloodhero.DonorPackage;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodhero.R;

public class viewHolderForPendingReqForDonor extends RecyclerView.ViewHolder {

    View mview;

 public    Button chckBtn ;
    public viewHolderForPendingReqForDonor(View itemView) {
        super(itemView);

        mview = itemView;

        //item click


    }

    public  void setDetails(String id,String date  )
    {
        Button acceptBtn , rejectBtn , bldBtn ;
        TextView pateintName , pateintNumber , pateientAdress , dateTv ;

        /*
        pateintName = (TextView) mview.findViewById(R.id.patientName);
        pateientAdress =(TextView)mview.findViewById(R.id.adress) ;
        pateintNumber = (TextView) mview.findViewById(R.id.pateientNumber);
        dateTv = mview.findViewById(R.id.time) ;
        bldBtn = mview.findViewById(R.id.requestedBlood);
        acceptBtn= mview.findViewById(R.id.acceptBtn);
        rejectBtn = mview.findViewById(R.id.rejectBtn);
    */
        TextView datee = mview.findViewById(R.id.time) ;
        TextView postID =mview.findViewById(R.id.post_ID) ;
        chckBtn = mview.findViewById(R.id.checkBtn) ;


        postID.setText(id);
        datee.setText(date);




    }

}
