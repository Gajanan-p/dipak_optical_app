package com.example.dipakopticalapp.webservices.customerpayment;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ICustomerPayment {
    @Headers("Content-Type: application/json")
    @GET("api/Values/CustomerPaymentList")
    Call<ArrayList<CustomerPaymentListModel>> getCustomerPaymentListModel();

    @POST("api/Values/SaveCustPayment")
    Call<SaveCustPaymentModel> setSaveCustPaymentModel(@Body SaveCustPaymentModel saveCustPayment);

    @POST("api/Values/DeletePayment")
    Call<DeletePayment> setDeletePayment(@Body DeletePayment deletePayment);

    @POST("api/Values/UpdateCustPayment")
    Call<UpdateCustPaymentModel> setUpdateCustPaymentModel(@Body UpdateCustPaymentModel updateCustPaymentModel);

    @POST("api/Values/GetPendingByCustId")
    Call<ArrayList<GetPendingByCustIdModel>> setGetPendingByCustId(@Body RequestGetPendingByCustId requestGetPendingByCustId);
}
