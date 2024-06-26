package com.example.dipakopticalapp.webservices.login;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ILogin {
    @Headers("Content-Type: application/json")
    @POST("api/Values/Login")
    Call<LoginModel> listUser(@Body LoginReq loginReq);
}
