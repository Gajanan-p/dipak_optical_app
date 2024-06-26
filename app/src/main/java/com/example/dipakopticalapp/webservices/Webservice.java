package com.example.dipakopticalapp.webservices;

import com.example.dipakopticalapp.webservices.bill.IBill;
import com.example.dipakopticalapp.webservices.billlist.IBillList;
import com.example.dipakopticalapp.webservices.customer.ISearchCustomer;
import com.example.dipakopticalapp.webservices.customerpayment.ICustomerPayment;
import com.example.dipakopticalapp.webservices.home.IDashboard;
import com.example.dipakopticalapp.webservices.login.ILogin;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Webservice {
    static Retrofit retrofit;
    public static String BASE_URL="http://gajanan.testapp.co.in/";
    static ILogin iLogin;
    static IDashboard iDashboard;
    static IBillList iBillList;
    static ISearchCustomer iSearchCustomer;
    static ICustomerPayment iCustomerPayment;
    static IBill iBill;

    public static Retrofit getRetrofitClient() {

        if(retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ILogin iLoginUser()
    {
        if (iLogin==null)
        {
            iLogin = getRetrofitClient().create(ILogin.class);
        }
        return iLogin;
    }

    public static IBillList getBillListData()
    {
        if (iBillList==null)
        {
            iBillList = getRetrofitClient().create(IBillList.class);
        }
        return iBillList;
    }

    public static ISearchCustomer getSearchCustomerData()
    {
        if (iSearchCustomer==null)
        {
            iSearchCustomer = getRetrofitClient().create(ISearchCustomer.class);
        }
        return iSearchCustomer;
    }

    public static ICustomerPayment getCustomerPaymentData()
    {
        if (iCustomerPayment==null)
        {
            iCustomerPayment = getRetrofitClient().create(ICustomerPayment.class);
        }
        return iCustomerPayment;
    }

    public static IBill setBillData()
    {
        if (iBill==null)
        {
            iBill = getRetrofitClient().create(IBill.class);
        }
        return iBill;
    }

    public static IDashboard getDashboard()
    {
        if (iDashboard==null){
            iDashboard = getRetrofitClient().create(IDashboard.class);
        }
        return iDashboard;
    }
}
