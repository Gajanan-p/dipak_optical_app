package com.example.dipakopticalapp.webservices.customer;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestSearchCustomer {

    @SerializedName("Name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestSearchCustomer() {
    }

    /**
     *
     * @param name
     */
    public RequestSearchCustomer(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}