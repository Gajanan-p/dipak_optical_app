package com.example.dipakopticalapp.webservices.customerpayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateCustPaymentModel {

    @SerializedName("CustomerPayId")
    @Expose
    private String customerPayId;
    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Advance")
    @Expose
    private String advance;
    @SerializedName("Total")
    @Expose
    private String total;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateCustPaymentModel() {
    }

    /**
     *
     * @param date
     * @param total
     * @param customerId
     * @param customerPayId
     * @param advance
     */
    public UpdateCustPaymentModel(String customerPayId, String customerId, String date, String advance, String total) {
        super();
        this.customerPayId = customerPayId;
        this.customerId = customerId;
        this.date = date;
        this.advance = advance;
        this.total = total;
    }

    public String getCustomerPayId() {
        return customerPayId;
    }

    public void setCustomerPayId(String customerPayId) {
        this.customerPayId = customerPayId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}