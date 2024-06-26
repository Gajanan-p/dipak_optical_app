package com.example.dipakopticalapp.webservices.bill;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqViewBillModel {

    @SerializedName("BillId")
    @Expose
    private Integer billId;

    /**
     * No args constructor for use in serialization
     *
     */
    public ReqViewBillModel() {
    }

    /**
     *
     * @param billId
     */
    public ReqViewBillModel(Integer billId) {
        super();
        this.billId = billId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

}

