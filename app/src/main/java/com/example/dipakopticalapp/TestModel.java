package com.example.dipakopticalapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestModel {

    @SerializedName("LedId")
    @Expose
    private String ledId;
    @SerializedName("Ledname")
    @Expose
    private String ledname;
    @SerializedName("abbrname")
    @Expose
    private String abbrname;
    @SerializedName("mobile1")
    @Expose
    private String mobile1;
    @SerializedName("smsmobile")
    @Expose
    private String smsmobile;

    /**
     * No args constructor for use in serialization
     *
     */
    public TestModel() {
    }

    /**
     *
     * @param abbrname
     * @param ledId
     * @param mobile1
     * @param ledname
     * @param smsmobile
     */
    public TestModel(String ledId, String ledname, String abbrname, String mobile1, String smsmobile) {
        super();
        this.ledId = ledId;
        this.ledname = ledname;
        this.abbrname = abbrname;
        this.mobile1 = mobile1;
        this.smsmobile = smsmobile;
    }

    public String getLedId() {
        return ledId;
    }

    public void setLedId(String ledId) {
        this.ledId = ledId;
    }

    public String getLedname() {
        return ledname;
    }

    public void setLedname(String ledname) {
        this.ledname = ledname;
    }

    public String getAbbrname() {
        return abbrname;
    }

    public void setAbbrname(String abbrname) {
        this.abbrname = abbrname;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getSmsmobile() {
        return smsmobile;
    }

    public void setSmsmobile(String smsmobile) {
        this.smsmobile = smsmobile;
    }
}
