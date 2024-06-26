package com.example.dipakopticalapp.webservices.customerpayment;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeletePayment {

    @SerializedName("CustomerPayId")
    @Expose
    private String customerPayId;

    /**
     * No args constructor for use in serialization
     *
     */
    public DeletePayment() {
    }

    /**
     *
     * @param customerPayId
     */
    public DeletePayment(String customerPayId) {
        super();
        this.customerPayId = customerPayId;
    }

    public String getCustomerPayId() {
        return customerPayId;
    }

    public void setCustomerPayId(String customerPayId) {
        this.customerPayId = customerPayId;
    }

}