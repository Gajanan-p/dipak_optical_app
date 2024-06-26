package com.example.dipakopticalapp.webservices.customerpayment;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CustomerPaymentListModel {

    @SerializedName("CustomerPayId")
    @Expose
    private Integer customerPayId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("Advance")
    @Expose
    private String advance;
    @SerializedName("Balance")
    @Expose
    private String balance;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
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
    public CustomerPaymentListModel() {
    }

    /**
     *
     * @param date
     * @param msg
     * @param total
     * @param balance
     * @param name
     * @param customerPayId
     * @param respCode
     * @param mobile
     * @param advance
     */
    public CustomerPaymentListModel(Integer customerPayId, String date, String name,
                                    String total, String advance, String balance, String mobile,
                                    String msg, String respCode) {
        super();
        this.customerPayId = customerPayId;
        this.date = date;
        this.name = name;
        this.total = total;
        this.advance = advance;
        this.balance = balance;
        this.mobile = mobile;
        this.msg = msg;
        this.respCode = respCode;
    }

    public Integer getCustomerPayId() {
        return customerPayId;
    }

    public void setCustomerPayId(Integer customerPayId) {
        this.customerPayId = customerPayId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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