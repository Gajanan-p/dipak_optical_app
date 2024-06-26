package com.example.dipakopticalapp.webservices.customerpayment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

 public class SaveCustPaymentModel {

    @SerializedName("CustomerId")
    @Expose
    private String customerId;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Advance")
    @Expose
    private String advance;
    @SerializedName("Balance")
    @Expose
    private String balance;

    /**
     * No args constructor for use in serialization
     *
     */
    public SaveCustPaymentModel() {
    }

    /**
     *
     * @param date
     * @param balance
     * @param customerId
     * @param advance
     */
    public SaveCustPaymentModel(String customerId, String date, String advance, String balance) {
        super();
        this.customerId = customerId;
        this.date = date;
        this.advance = advance;
        this.balance = balance;
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}