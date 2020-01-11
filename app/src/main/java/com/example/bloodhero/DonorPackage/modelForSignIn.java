package com.example.bloodhero.DonorPackage;

public class modelForSignIn {
    String username , pass ;

    public modelForSignIn() {
    }

    public modelForSignIn(String username, String pass) {
        this.username = username;
        this.pass = pass;
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
}
