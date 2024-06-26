package com.example.dipakopticalapp.webservices.home;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashboardByDateModel {

    @SerializedName("TotalBill")
    @Expose
    private Integer totalBill;
    @SerializedName("TotalAmt")
    @Expose
    private String totalAmt;
    @SerializedName("PendingAmt")
    @Expose
    private String pendingAmt;
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
    public DashboardByDateModel() {
    }

    /**
     *
     * @param msg
     * @param totalAmt
     * @param totalBill
     * @param pendingAmt
     * @param respCode
     */
    public DashboardByDateModel(Integer totalBill, String totalAmt, String pendingAmt, String msg, String respCode) {
        super();
        this.totalBill = totalBill;
        this.totalAmt = totalAmt;
        this.pendingAmt = pendingAmt;
        this.msg = msg;
        this.respCode = respCode;
    }

    public Integer getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(Integer totalBill) {
        this.totalBill = totalBill;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getPendingAmt() {
        return pendingAmt;
    }

    public void setPendingAmt(String pendingAmt) {
        this.pendingAmt = pendingAmt;
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