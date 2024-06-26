package com.example.dipakopticalapp.webservices.login;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("ShopName")
    @Expose
    private String shopName;
    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Msg")
    @Expose
    private String msg;
    @SerializedName("RespCode")
    @Expose
    private String respCode;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginModel() {
    }

    /**
     *
     * @param msg
     * @param shopName
     * @param mobileNo
     * @param userId
     * @param respCode
     * @param status
     */
    public LoginModel(Integer userId, String mobileNo, String shopName, Boolean status, String msg, String respCode) {
        super();
        this.userId = userId;
        this.mobileNo = mobileNo;
        this.shopName = shopName;
        this.status = status;
        this.msg = msg;
        this.respCode = respCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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