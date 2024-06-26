package com.example.dipakopticalapp.webservices.customer;



import com.example.dipakopticalapp.TestModel;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ISearchCustomer {
    @Headers("Content-Type: application/json")
    @GET("api/Values/SearchCustomer")
    Call<ArrayList<SearchCustomerModel>> getSearchCustomerModel();

    @Headers("Content-Type: application/json")
    @GET("api/Values/SearchCustomer")
    Call<ArrayList<TestModel>> getTestModel();
}
