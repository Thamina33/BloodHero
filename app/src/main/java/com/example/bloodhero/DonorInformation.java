package com.example.bloodhero;

public class DonorInformation {

    String id,uid,name,mail,gender,bloodgroup,visibility;

    public DonorInformation() {
    }

    public DonorInformation(String id, String uid, String name, String mail, String gender, String bloodgroup, String visibility) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.visibility = visibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
