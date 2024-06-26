package com.example.dipakopticalapp.webservices.bill;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewBillModel {

    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("RE_Dist_SPH")
    @Expose
    private String rEDistSPH;
    @SerializedName("RE_Dist_CYL")
    @Expose
    private String rEDistCYL;
    @SerializedName("RE_Dist_AXIS")
    @Expose
    private String rEDistAXIS;
    @SerializedName("RE_Dist_VA")
    @Expose
    private String rEDistVA;
    @SerializedName("RE_Near_ADD")
    @Expose
    private String rENearADD;
    @SerializedName("LE_Dist_SPH")
    @Expose
    private String lEDistSPH;
    @SerializedName("LE_Dist_CYL")
    @Expose
    private String lEDistCYL;
    @SerializedName("LE_Dist_AXIS")
    @Expose
    private String lEDistAXIS;
    @SerializedName("LE_Dist_VA")
    @Expose
    private String lEDistVA;
    @SerializedName("LE_Near_ADD")
    @Expose
    private String lENearADD;
    @SerializedName("Glass")
    @Expose
    private String glass;
    @SerializedName("Frame")
    @Expose
    private String frame;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("Advance")
    @Expose
    private String advance;
    @SerializedName("Balance")
    @Expose
    private String balance;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Age")
    @Expose
    private String age;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Gender")
    @Expose
    private String gender;
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
    public ViewBillModel() {
    }

    /**
     *
     * @param date
     * @param msg
     * @param glass
     * @param lEDistSPH
     * @param address
     * @param gender
     * @param mobile
     * @param lEDistAXIS
     * @param advance
     * @param total
     * @param rENearADD
     * @param rEDistCYL
     * @param balance
     * @param customerId
     * @param name
     * @param rEDistAXIS
     * @param lEDistCYL
     * @param rEDistSPH
     * @param lENearADD
     * @param lEDistVA
     * @param age
     * @param respCode
     * @param rEDistVA
     * @param frame
     */
    public ViewBillModel(String date, Integer customerId, String rEDistSPH, String rEDistCYL, String rEDistAXIS, String rEDistVA, String rENearADD, String lEDistSPH, String lEDistCYL, String lEDistAXIS, String lEDistVA, String lENearADD, String glass, String frame, String total, String advance, String balance, String name, String age, String mobile, String address, String gender, String msg, String respCode) {
        super();
        this.date = date;
        this.customerId = customerId;
        this.rEDistSPH = rEDistSPH;
        this.rEDistCYL = rEDistCYL;
        this.rEDistAXIS = rEDistAXIS;
        this.rEDistVA = rEDistVA;
        this.rENearADD = rENearADD;
        this.lEDistSPH = lEDistSPH;
        this.lEDistCYL = lEDistCYL;
        this.lEDistAXIS = lEDistAXIS;
        this.lEDistVA = lEDistVA;
        this.lENearADD = lENearADD;
        this.glass = glass;
        this.frame = frame;
        this.total = total;
        this.advance = advance;
        this.balance = balance;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.address = address;
        this.gender = gender;
        this.msg = msg;
        this.respCode = respCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getREDistSPH() {
        return rEDistSPH;
    }

    public void setREDistSPH(String rEDistSPH) {
        this.rEDistSPH = rEDistSPH;
    }

    public String getREDistCYL() {
        return rEDistCYL;
    }

    public void setREDistCYL(String rEDistCYL) {
        this.rEDistCYL = rEDistCYL;
    }

    public String getREDistAXIS() {
        return rEDistAXIS;
    }

    public void setREDistAXIS(String rEDistAXIS) {
        this.rEDistAXIS = rEDistAXIS;
    }

    public String getREDistVA() {
        return rEDistVA;
    }

    public void setREDistVA(String rEDistVA) {
        this.rEDistVA = rEDistVA;
    }

    public String getRENearADD() {
        return rENearADD;
    }

    public void setRENearADD(String rENearADD) {
        this.rENearADD = rENearADD;
    }

    public String getLEDistSPH() {
        return lEDistSPH;
    }

    public void setLEDistSPH(String lEDistSPH) {
        this.lEDistSPH = lEDistSPH;
    }

    public String getLEDistCYL() {
        return lEDistCYL;
    }

    public void setLEDistCYL(String lEDistCYL) {
        this.lEDistCYL = lEDistCYL;
    }

    public String getLEDistAXIS() {
        return lEDistAXIS;
    }

    public void setLEDistAXIS(String lEDistAXIS) {
        this.lEDistAXIS = lEDistAXIS;
    }

    public String getLEDistVA() {
        return lEDistVA;
    }

    public void setLEDistVA(String lEDistVA) {
        this.lEDistVA = lEDistVA;
    }

    public String getLENearADD() {
        return lENearADD;
    }

    public void setLENearADD(String lENearADD) {
        this.lENearADD = lENearADD;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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