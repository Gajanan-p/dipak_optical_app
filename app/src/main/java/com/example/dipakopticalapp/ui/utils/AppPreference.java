package com.example.dipakopticalapp.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.dipakopticalapp.webservices.billlist.BillListModel;
import com.example.dipakopticalapp.webservices.customer.SearchCustomerModel;
import com.example.dipakopticalapp.webservices.customerpayment.CustomerPaymentListModel;
import com.example.dipakopticalapp.webservices.login.LoginModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppPreference {

    private static String TAG="appDataPreferences";
    public static String appPreferences="appPreferences";

    public static String appLoginPreferences="appLoginPreferences";

    public static String appLoginPreferencesKey="appLoginPreferencesKey";
    public static String appSearchCustomerDataPreferencesKey="appSearchCustomerDataPreferencesKey";
    public static String appCustomerPaymentDataPreferencesKey="appCustomerPaymentDataPreferencesKey";
    public static String appBillListDataPreferencesKey="appBillListDataPreferencesKey";

    public static SharedPreferences sharedLoginPreferences;
    public static SharedPreferences sharedSettingPreferences;
    public static SharedPreferences sharedAppPreferences;

    public static SharedPreferences getAppSharedPreferences(Context context){
        if(sharedAppPreferences==null){
            sharedAppPreferences=context.getSharedPreferences(AppPreference.appPreferences
                    , Context.MODE_PRIVATE);
        }
        return sharedAppPreferences;
    }

    public static SharedPreferences getLoginSharedPreferences(Context context){
        if(sharedLoginPreferences==null){
            sharedLoginPreferences=context.getSharedPreferences(AppPreference.appLoginPreferences, Context.MODE_PRIVATE);
        }
        return sharedLoginPreferences;
    }


    public static void setLoginDataPreferences(Context context, LoginModel loginModel){
        Gson gson = new Gson();
        String data=gson.toJson(loginModel);
        Log.i(TAG,"Set user model data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getLoginSharedPreferences(context).edit();
        editor.putString(appLoginPreferencesKey,data);
        editor.commit();
    }

    public static LoginModel getLoginDataPreferences(Context context){
        String data=getLoginSharedPreferences(context).getString(appLoginPreferencesKey,"");
        Gson gson = new Gson();
        LoginModel loginModel= gson.fromJson(data,LoginModel.class);
        Log.i(TAG,"get user model data");
        Log.i(TAG,data);
        return loginModel;
    }
    public static boolean clearLoginDataPreferences(Context context){
        SharedPreferences.Editor editor=getLoginSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
        editor.commit();
        Log.i(TAG,"Clear login Preferences");
        return true;
    }

    public static void setSearchCustomerDataPreferences(Context context, SearchCustomerModel searchCustomerModel){
        Gson gson = new Gson();
        String data=gson.toJson(searchCustomerModel);
        Log.i(TAG,"Set user model data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.putString(appSearchCustomerDataPreferencesKey,data);
        editor.commit();
    }
    public static SearchCustomerModel getSearchCustomerDataPreferences(Context context){
        String data=getAppSharedPreferences(context).getString(appSearchCustomerDataPreferencesKey,"");
        Gson gson = new Gson();
        SearchCustomerModel searchCustomerModel= gson.fromJson(data,SearchCustomerModel.class);
        Log.i(TAG,"get user model data");
        Log.i(TAG,data);
        return searchCustomerModel;
    }
    public static void setCustomerPaymentDataPreferences(Context context, CustomerPaymentListModel customerPaymentListModel){
        Gson gson = new Gson();
        String data=gson.toJson(customerPaymentListModel);
        Log.i(TAG,"Set user model data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.putString(appCustomerPaymentDataPreferencesKey,data);
        editor.commit();
    }
    public static CustomerPaymentListModel getCustomerPaymentDataPreferences(Context context){
        String data=getAppSharedPreferences(context).getString(appCustomerPaymentDataPreferencesKey,"");
        Gson gson = new Gson();
        CustomerPaymentListModel customerPaymentListModel= gson.fromJson(data,CustomerPaymentListModel.class);
        Log.i(TAG,"get user model data");
        Log.i(TAG,data);
        return customerPaymentListModel;
    }
    public static void setBillListPreferences(Context context, BillListModel billListModel){
        Gson gson = new Gson();
        String data=gson.toJson(billListModel);
        Log.i(TAG,"Set Bill List Data");
        Log.i(TAG,data);
        SharedPreferences.Editor editor=getAppSharedPreferences(context).edit();
        editor.putString(appBillListDataPreferencesKey,data);
        editor.commit();
    }

    public static BillListModel getBillListPreferences(Context context){
        String data=getAppSharedPreferences(context).getString(appBillListDataPreferencesKey,"");
        Gson gson = new Gson();
        BillListModel billListModel= gson.fromJson(data,BillListModel.class);
        Log.i(TAG,"get Bill List data "+ data);
        Log.i(TAG,data);
        return billListModel;
    }

}
