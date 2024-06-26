package com.example.dipakopticalapp.webservices.bill;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IBill {
    @POST("api/Values/SaveUpdateBill")
    Call<SaveBillModel> setSaveBillModel(@Body SaveBillModel saveBillModel);

    @POST("api/Values/ViewBill")
    Call<ArrayList<ViewBillModel>> setViewBillModel(@Body ReqViewBillModel reqViewBillModel);


}