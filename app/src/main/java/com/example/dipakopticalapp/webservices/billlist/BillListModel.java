package com.example.dipakopticalapp.webservices.billlist;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillListModel {

    @SerializedName("BillId")
    @Expose
    private Integer billId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("Advance")
    @Expose
    private String advance;
    @SerializedName("Name")
    @Expose
    private String name;
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
    public BillListModel() {
    }

    /**
     *
     * @param date
     * @param msg
     * @param total
     * @param billId
     * @param name
     * @param mobile
     * @param respCode
     * @param advance
     */
    public BillListModel(Integer billId, String date, String total, String advance, String name, String mobile, String msg, String respCode) {
        super();
        this.billId = billId;
        this.date = date;
        this.total = total;
        this.advance = advance;
        this.name = name;
        this.mobile = mobile;
        this.msg = msg;
        this.respCode = respCode;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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