package com.example.bloodhero.DonorPackage;

public class modelForpedingReqForDonor {
    String postID , date , status ;

    public modelForpedingReqForDonor() {
    }

    public modelForpedingReqForDonor(String postID, String date, String status) {
        this.postID = postID;
        this.date = date;
        this.status = status;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
