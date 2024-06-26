package com.example.dipakopticalapp.webservices.billlist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IBillList {
    @Headers("Content-Type: application/json")
    @GET("api/Values/BillList")
    Call<ArrayList<BillListModel>> getBillListModel();

    @POST("api/Values/DeleteBill")
    Call<DeleteBillModel> setDeleteBillModel(@Body DeleteBillModel deleteBill);
}
