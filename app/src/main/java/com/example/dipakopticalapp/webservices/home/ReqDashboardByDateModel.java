package com.example.dipakopticalapp.webservices.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqDashboardByDateModel {

    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReqDashboardByDateModel() {
    }

    /**
     *
     * @param fromDate
     * @param toDate
     */
    public ReqDashboardByDateModel(String fromDate, String toDate) {
        super();
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}