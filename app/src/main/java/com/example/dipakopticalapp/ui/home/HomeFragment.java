package com.example.dipakopticalapp.ui.home;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.webservices.home.DashboardByDateModel;
import com.example.dipakopticalapp.webservices.home.DashboardValueModel;
import com.example.dipakopticalapp.webservices.home.ReqDashboardByDateModel;
import com.example.dipakopticalapp.webservices.Webservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements
        View.OnClickListener,IDashboardValueListener{

    private ProgressDialog progressDialog;
    DashboardValueModel dashboardValueModel;
    DashboardByDateModel dashboardByDateModel;

    NavController navController;
    AppCompatButton buttonNewBill;
    AppCompatButton buttonRefresh;
    AppCompatButton buttonSearch;
    AppCompatButton buttonAddPay;
    AppCompatTextView editTextFromDate;
    AppCompatTextView editTextToDate;
    AppCompatTextView textViewTodayTotalBill;
    AppCompatTextView textViewTodayTotalAmt;
    AppCompatTextView textViewTodayPendingAmt;
    AppCompatTextView textViewTotalPendingAmt;
    AppCompatTextView textViewAmount,textViewTotalBill;

    Calendar calendar1,calendar;
    SimpleDateFormat simpleDateFormat;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonNewBill = view.findViewById(R.id.button_home_new_bill);
        buttonNewBill.setOnClickListener(this);

//        buttonRefresh= view.findViewById(R.id.button_home_refresh);
//        buttonRefresh.setOnClickListener(this);

        buttonSearch = view.findViewById(R.id.button_home_search);
        buttonSearch.setOnClickListener(this);

        buttonAddPay= view.findViewById(R.id.button_home_add_pay);
        buttonAddPay.setOnClickListener(this);

        editTextFromDate = view.findViewById(R.id.edit_home_from_date);
        editTextFromDate.setOnClickListener(this);
        editTextToDate = view.findViewById(R.id.edit_home_to_date);
        editTextToDate.setOnClickListener(this);
        textViewTodayTotalBill = view.findViewById(R.id.text_home_today_total_bill);
        textViewTodayTotalAmt = view.findViewById(R.id.text_home_today_total_amt);
        textViewTodayPendingAmt = view.findViewById(R.id.text_home_today_pending_amt);
        textViewTotalPendingAmt = view.findViewById(R.id.text_home_total_pending_amt);
        textViewTotalBill = view.findViewById(R.id.text_home_total_bill);
        textViewAmount = view.findViewById(R.id.text_home_amt);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String currentDate = simpleDateFormat.format(calendar.getTime());
        editTextToDate.setText(currentDate);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault());
        String yesterdayAsString = dateFormat.format(calendar1.getTime());
        editTextFromDate.setText(yesterdayAsString);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
      //  homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }
    /*----------Stock Correction Fragment------------------------------*/
    public void onNewBillButtonClicked() {
        navController.navigate(R.id.action_nav_home_to_nav_billListFragment);
    }
    public void onAddPayButtonClicked() {
        navController.navigate(R.id.action_nav_home_to_nav_customerPaymentFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        getTodayBillValue();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_home_new_bill: {
                onNewBillButtonClicked();
                break;
            }
            case R.id.button_home_add_pay: {
                onAddPayButtonClicked();
                break;
            }
            case R.id.button_home_search: {
                getTodayBillDateWise();
                break;
            }
            case R.id.edit_home_from_date: {
                getTodayFromDateWise();
                break;
            }
            case R.id.edit_home_to_date: {
                getTodayToDateWise();
                break;
            }
//            case R.id.button_home_refresh: {
//                getRefreshHomePage();
//                break;
//            }

        }
    }

    void getTodayBillValue(){
        progressDialog = createProgressDialog(getContext());
        Call<DashboardValueModel> callDashboard = Webservice
                .getDashboard()
                .getDashboardValueModel();
        callDashboard.enqueue(new Callback<DashboardValueModel>() {
            @Override
            public void onResponse(Call<DashboardValueModel> call, Response<DashboardValueModel> response) {
                dashboardValueModel = response.body();
                if(dashboardValueModel!=null) {
                    onItemClicked(dashboardValueModel);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DashboardValueModel> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });

    }
    void onItemClicked(DashboardValueModel dashboardValueModel) {
        textViewTodayTotalBill.setText(dashboardValueModel.getTotalBill().toString());
        textViewTodayTotalAmt.setText(dashboardValueModel.getTotalAmt());
        textViewTodayPendingAmt .setText(dashboardValueModel.getPendingAmt());
    }
    void getTodayBillDateWise(){
        progressDialog = createProgressDialog(getContext());
        ReqDashboardByDateModel reqDashboardByDateModel = new ReqDashboardByDateModel();

        reqDashboardByDateModel.setFromDate(editTextFromDate.getText().toString());
        reqDashboardByDateModel.setToDate(editTextToDate.getText().toString());
        Call<DashboardByDateModel> arrayListCall = Webservice
                .getDashboard()
                .listDashboardByDateModel(reqDashboardByDateModel);
        arrayListCall.enqueue(new Callback<DashboardByDateModel>() {
            @Override
            public void onResponse(Call<DashboardByDateModel> call, Response<DashboardByDateModel> response) {
                dashboardByDateModel = response.body();
                if(dashboardByDateModel!=null) {
                    setClickValue(dashboardByDateModel);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DashboardByDateModel> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });
    }
    void setClickValue(DashboardByDateModel dashboardByDateModel){
        textViewTotalBill.setText(dashboardByDateModel.getTotalBill().toString());
        textViewAmount.setText(dashboardByDateModel.getTotalAmt());
        textViewTotalPendingAmt.setText(dashboardByDateModel.getPendingAmt());
    }

    @Override
    public void onItemValueClicked(DashboardValueModel dashboardValueModel) {
        textViewTodayTotalAmt.setText(dashboardValueModel.getTotalAmt());
    }
    private void getTodayFromDateWise(){
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //todo
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        editTextFromDate.setText(simpleDateFormat.format(newDate.getTime()));

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void getTodayToDateWise() {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //todo
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, dayOfMonth);
                        editTextToDate.setText(simpleDateFormat.format(newDate.getTime()));

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    void getRefreshHomePage(){
        if (dashboardValueModel!=null){
            getTodayBillValue();
        }

    }
    public ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.blue(100)));
        dialog.setContentView(R.layout.dialog_progress_layout);
        return dialog;
    }
}
