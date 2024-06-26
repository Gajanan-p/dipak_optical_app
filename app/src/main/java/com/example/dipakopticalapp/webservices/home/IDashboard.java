package com.example.dipakopticalapp.webservices.home;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IDashboard {
    @Headers("Content-Type: application/json")
    @GET("api/Values/DashboardValue")
    Call<DashboardValueModel> getDashboardValueModel();

    @Headers("Content-Type: application/json")
    @POST("api/Values/DashboardByDate")
    Call<DashboardByDateModel> listDashboardByDateModel(@Body ReqDashboardByDateModel reqDashboardByDateModel);
}
