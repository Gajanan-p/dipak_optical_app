package com.example.dipakopticalapp.webservices.customerpayment;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GetPendingByCustIdModel {

    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("Balance")
    @Expose
    private String balance;
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
    public GetPendingByCustIdModel() {
    }

    /**
     *
     * @param msg
     * @param balance
     * @param customerId
     * @param respCode
     */
    public GetPendingByCustIdModel(Integer customerId, String balance, String msg, String respCode) {
        super();
        this.customerId = customerId;
        this.balance = balance;
        this.msg = msg;
        this.respCode = respCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
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