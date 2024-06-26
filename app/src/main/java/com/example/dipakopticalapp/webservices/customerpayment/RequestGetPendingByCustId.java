package com.example.dipakopticalapp.webservices.customerpayment;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestGetPendingByCustId {

    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestGetPendingByCustId() {
    }

    /**
     *
     * @param customerId
     */
    public RequestGetPendingByCustId(Integer customerId) {
        super();
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}