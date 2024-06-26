package com.example.dipakopticalapp.webservices.billlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteBillModel {

    @SerializedName("BillId")
    @Expose
    private String billId;

    /**
     * No args constructor for use in serialization
     *
     */
    public DeleteBillModel() {
    }

    /**
     *
     * @param billId
     */
    public DeleteBillModel(String billId) {
        super();
        this.billId = billId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

}