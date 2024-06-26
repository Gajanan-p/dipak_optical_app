package com.example.dipakopticalapp.webservices.customer;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SearchCustomerModel {

    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Msg")
    @Expose
    private String msg;
    @SerializedName("RespCode")
    @Expose
    private String respCode;

    public SearchCustomerModel(){}

    public SearchCustomerModel(Integer customerId, String name, String age, String mobile,
                               String address, String gender, String status, String msg, String respCode) {
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
        this.gender = gender;
        this.status = status;
        this.msg = msg;
        this.respCode = respCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
}
