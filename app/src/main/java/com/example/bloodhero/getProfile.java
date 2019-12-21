package com.example.bloodhero;

public class getProfile {

    String id,uid,name,mail,gender,bloodgroup,num_visibility , is_available , imageLink ,username , pass , co_ordName , status ,nottificationID  ;

    public getProfile() {
    }

    public getProfile(String id, String uid, String name, String mail, String gender,
                      String bloodgroup, String num_visibility, String is_available,
                      String imageLink, String username, String pass,
                      String co_ordName, String status, String nottificationID) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.mail = mail;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.num_visibility = num_visibility;
        this.is_available = is_available;
        this.imageLink = imageLink;
        this.username = username;
        this.pass = pass;
        this.co_ordName = co_ordName;
        this.status = status;
        this.nottificationID = nottificationID;
    }

    public String getNottificationID() {
        return nottificationID;
    }

    public void setNottificationID(String nottificationID) {
        this.nottificationID = nottificationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCo_ordName() {
        return co_ordName;
    }

    public void setCo_ordName(String co_ordName) {
        this.co_ordName = co_ordName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
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
