package com.example.bloodhero;

public class getProfile {

    String id,uid,name,mail,gender,bloodgroup,num_visibility , is_available , user_pp;

    public getProfile() {
    }

    public getProfile(String id, String uid, String name, String mail, String gender, String bloodgroup, String num_visibility, String is_available, String user_pp) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.num_visibility = num_visibility;
        this.is_available = is_available;
        this.user_pp = user_pp;
    }

    public String getUser_pp() {
        return user_pp;
    }

    public void setUser_pp(String user_pp) {
        this.user_pp = user_pp;
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
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

    public String getNum_visibility() {
        return num_visibility;
    }

    public void setNum_visibility(String num_visibility) {
        this.num_visibility = num_visibility;
    }
}
