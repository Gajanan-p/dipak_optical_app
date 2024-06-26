package com.example.dipakopticalapp.webservices.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginReq {

    @SerializedName("MOBILENO")
    @Expose
    private String mobileno;
    @SerializedName("PASSWORD")
    @Expose
    private String password;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginReq() {
    }

    /**
     *
     * @param password
     * @param mobileno
     */
    public LoginReq(String mobileno, String password) {
        super();
        this.mobileno = mobileno;
        this.password = password;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}