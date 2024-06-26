package com.example.dipakopticalapp.ui.bill;



import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.gender.GenderDialog;
import com.example.dipakopticalapp.spinner.gender.GenderModel;
import com.example.dipakopticalapp.spinner.gender.IGenderListener;
import com.example.dipakopticalapp.spinner.leaxis.ILEAxisListener;
import com.example.dipakopticalapp.spinner.leaxis.LEAxisDialog;
import com.example.dipakopticalapp.spinner.leaxis.LEAxisModel;
import com.example.dipakopticalapp.spinner.leva.ILEVAListener;
import com.example.dipakopticalapp.spinner.leva.LEVADialog;
import com.example.dipakopticalapp.spinner.leva.LEVAModel;
import com.example.dipakopticalapp.spinner.reaxis.IREAxisListener;
import com.example.dipakopticalapp.spinner.reaxis.REAxisDialog;
import com.example.dipakopticalapp.spinner.reaxis.REAxisModel;
import com.example.dipakopticalapp.spinner.reva.IREVAListener;
import com.example.dipakopticalapp.spinner.reva.REVADialog;
import com.example.dipakopticalapp.spinner.reva.REVAModel;
import com.example.dipakopticalapp.webservices.bill.SaveBillModel;
import com.example.dipakopticalapp.webservices.customer.SearchCustomerModel;
import com.example.dipakopticalapp.webservices.Webservice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillingFragment extends Fragment implements
        View.OnClickListener, ILEVAListener, IGenderListener,
        ILEAxisListener, IREVAListener, IREAxisListener,
        CustomListener{

    AppCompatEditText editTextMobileNo,editTextAddress,editTextAge;
    AppCompatTextView editTextGender,editTextLEAxis,editTextLEVA,editTextREAxis,editTextREVA,editTextDate;
    AppCompatEditText editTextRESPH,editTextRECYL,editTextRENearAdd,
            editTextLESPH,editTextLECYL,editTextLENearAdd,editTextGlass,editTextFrame,editTextGlassPrice,
            editTextFramePrice,editTextTotal,editTextAdvance,editTextBalance;
    AutoCompleteTextView editTextCustomerName;
    AppCompatButton buttonSubmit,buttonCancel;
    LinearLayout controllerCustomerName;
    LinearLayout controllerRecyclerView;
    private SearchNameAdapter searchNameAdapter;
    private RecyclerView recyclerView;
    ArrayList<SearchCustomerModel> tempFilteredList = new ArrayList<>();
    private ArrayList<SearchCustomerModel> searchCustomerModels;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    private GenderDialog genderDialog;
    private LEVADialog levaDialog;
    private LEAxisDialog leAxisDialog;
    private REAxisDialog reAxisDialog;
    private REVADialog revaDialog;
    public static BillingFragment newInstance() {
        return new BillingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.billing_fragment, container, false);

        editTextAddress = view.findViewById(R.id.edit_bill_customer_address);
        editTextMobileNo = view.findViewById(R.id.edit_bill_customer_mobile_no);
        editTextDate = view.findViewById(R.id.edit_bill_customer_date);
        editTextDate.setOnClickListener(this);
        editTextGender  = view.findViewById(R.id.edit_bill_customer_gender);
        editTextGender.setOnClickListener(this);
        editTextAge = view.findViewById(R.id.edit_bill_customer_age);
        editTextRESPH = view.findViewById(R.id.edit_bill_re_sph);
        editTextREAxis = view.findViewById(R.id.edit_bill_re_axis);
        editTextREAxis.setOnClickListener(this);
        editTextREVA = view.findViewById(R.id.edit_bill_re_va);
        editTextREVA.setOnClickListener(this);
        editTextRECYL = view.findViewById(R.id.edit_bill_re_cyl);
        editTextRENearAdd = view.findViewById(R.id.edit_bill_re_near_add);
        editTextLESPH = view.findViewById(R.id.edit_bill_le_sph);
        editTextLEAxis = view.findViewById(R.id.edit_bill_le_axis);
        editTextLEAxis.setOnClickListener(this);
        editTextLEVA= view.findViewById(R.id.edit_bill_le_va);
        editTextLEVA.setOnClickListener(this);
        editTextLECYL= view.findViewById(R.id.edit_bill_le_cyl);
        editTextLENearAdd= view.findViewById(R.id.edit_bill_le_near_add);
        editTextGlass= view.findViewById(R.id.edit_bill_glass);
        editTextGlassPrice = view.findViewById(R.id.edit_bill_glass_price);
        editTextFrame= view.findViewById(R.id.edit_bill_frame);
        editTextFramePrice= view.findViewById(R.id.edit_bill_frame_price);
        editTextTotal= view.findViewById(R.id.edit_bill_total);
        editTextAdvance= view.findViewById(R.id.edit_bill_advance);
        editTextBalance= view.findViewById(R.id.edit_bill_balance);
        editTextBalance.setOnClickListener(this);
        buttonSubmit = view.findViewById(R.id.button_bill_submit);
        buttonSubmit.setOnClickListener(this);
        buttonCancel = view.findViewById(R.id.button_bill_cancel);
        recyclerView = (RecyclerView)view. findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        controllerCustomerName = view.findViewById(R.id.linear_billing_customer_name);
        controllerRecyclerView = view.findViewById(R.id.linear_billing_recycle);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy  hh:mm");
        String currentDate = simpleDateFormat.format(calendar.getTime());
        editTextDate.setText(currentDate);
        editTextCustomerName = view.findViewById(R.id.edit_bill_customer_name);
        editTextCustomerName.setOnClickListener(this);
        editTextAdvance.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                if (editTextAdvance.getText().toString().trim().length()>0) {
                    Double a = Double.parseDouble(editTextTotal.getText().toString().trim());
                    Double b = Double.parseDouble(editTextAdvance.getText().toString().trim());
                    editTextBalance.setText(String.valueOf(a - b));
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // mViewModel = new ViewModelProvider(this).get(BillingViewModel.class);
        // TODO: Use the ViewModel
    }
    public void onCustomerNameFilter(){
        controllerCustomerName.setVisibility(View.VISIBLE);
        controllerRecyclerView.setVisibility(View.GONE);
    }
    public void onWithoutCustomerNameFilter(){
        controllerCustomerName.setVisibility(View.VISIBLE);
        controllerRecyclerView.setVisibility(View.VISIBLE);
    }
    @Override
    public void onResume() {
        super.onResume();
         openCustomerList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_bill_customer_name:{
               // openCustomerListDialog();
                break;
            }
            case R.id.button_bill_submit:{
                createBillData();
                break;
            }
            case R.id.button_bill_cancel:{
                clearData();
                break;
            }
            case R.id.edit_bill_re_axis:{
                onREAxisClick(0);
                break;
            }
            case R.id.edit_bill_re_va:{
                onREVAContent(0);
                break;
            }
            case R.id.edit_bill_le_axis:{
                onLEAxisClick(0);
                break;
            }
            case R.id.edit_bill_le_va:{
                onLEVAContent(0);
                break;
            }
            case R.id.edit_bill_customer_gender:{
                onGenderItem(0);
                break;
            }
            case R.id.edit_bill_customer_date:{
                getTodayToDateWise();
                break;
            }

        }
    }

    public void onREAxisClick(int position) {
        reAxisDialog = new REAxisDialog(
                getContext(),
                REAxisModel.getREAxisModelList(),
                BillingFragment.this,
                position
        );
        reAxisDialog.show();

    }

    public void onREVAContent(int position){
        revaDialog = new REVADialog(
                getContext(),
                REVAModel.getREVAModelList(),
                BillingFragment.this,
                position
        );
        revaDialog.show();
    }

    public void openCustomerList() {
        // progressDialog = createProgressDialog(getContext());
        Call<ArrayList<SearchCustomerModel>> arrayListCall = Webservice
                .getSearchCustomerData()
                .getSearchCustomerModel();
        arrayListCall.enqueue(new Callback<ArrayList<SearchCustomerModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchCustomerModel>> call, Response<ArrayList<SearchCustomerModel>> response) {
               // tempFilteredList =new ArrayList<>( response.body());
                searchCustomerModels = response.body();

                ArrayAdapter searchAdapter = new ArrayAdapter(getActivity(),
                        R.layout.dialog_customer_name,R.id.edit_text_dialog_item,
                        getCustomerName(searchCustomerModels));
                editTextCustomerName.setThreshold(1);
                editTextCustomerName.setAdapter(searchAdapter);
                editTextCustomerName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object item = parent.getItemAtPosition(position);
                        for (int i=0;i<searchCustomerModels.size();i++){
                            if (item.equals(searchCustomerModels.get(i).getName())) {
                                SearchCustomerModel customerNameModel = searchCustomerModels.get(i);
                                editTextMobileNo.setText(customerNameModel.getMobile());
                                editTextAddress.setText(customerNameModel.getAddress());
                                editTextAge.setText(customerNameModel.getAge());
                                editTextGender.setText(customerNameModel.getGender());
                            }
                        }
                    }
                });
//                onCustomerNameFilter();
            }

            @Override
            public void onFailure(Call<ArrayList<SearchCustomerModel>> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }
    public ArrayList<String> getCustomerName(ArrayList<SearchCustomerModel> customerNameModels) {
        ArrayList<String> tempCustomerNameList = new ArrayList<>();
        for (int i = 0; i < customerNameModels.size(); i++) {
            tempCustomerNameList.add(customerNameModels.get(i).getName());
        }
        return tempCustomerNameList;
    }

    public void createBillData(){

        SaveBillModel saveBillModel = new SaveBillModel();
        saveBillModel.setDate(editTextDate.getText().toString());
        saveBillModel.setCustomerId("0");
        saveBillModel.setREDistSPH(editTextRESPH.getText().toString());
        saveBillModel.setREDistAXIS(editTextREAxis.getText().toString());
        saveBillModel.setREDistCYL(editTextRECYL.getText().toString());
        saveBillModel.setREDistVA(editTextREVA.getText().toString());
        saveBillModel.setRENearADD(editTextRENearAdd.getText().toString());
        saveBillModel.setLEDistSPH(editTextLESPH.getText().toString());
        saveBillModel.setLEDistAXIS(editTextLEAxis.getText().toString());
        saveBillModel.setLEDistCYL(editTextLECYL.getText().toString());
        saveBillModel.setLEDistVA(editTextLEVA.getText().toString());
        saveBillModel.setLENearADD(editTextLENearAdd.getText().toString());
        saveBillModel.setGlass(editTextGlass.getText().toString());
        saveBillModel.setFrame(editTextFrame.getText().toString());
        saveBillModel.setTotal(editTextTotal.getText().toString());
        saveBillModel.setAdvance(editTextAdvance.getText().toString());
        saveBillModel.setBalance(editTextBalance.getText().toString());
        saveBillModel.setName(editTextCustomerName.getText().toString());
        saveBillModel.setAge(editTextAge.getText().toString());
        saveBillModel.setMobile(editTextMobileNo.getText().toString());
        saveBillModel.setAddress(editTextAddress.getText().toString());
       // saveBillModel.setGender(editTextGender.getText().toString());

        saveBillModel.setBillId("0");
        Call<SaveBillModel> saveBillModelCall = Webservice
                .setBillData()
                .setSaveBillModel(saveBillModel);
        saveBillModelCall.enqueue(new Callback<SaveBillModel>() {
            @Override
            public void onResponse(Call<SaveBillModel> call, Response<SaveBillModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),"Save Successfully",Toast.LENGTH_LONG).show();
                    clearData();
                    onCustomerNameFilter();
                }
            }

            @Override
            public void onFailure(Call<SaveBillModel> call, Throwable t) {
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
    void clearData(){
        editTextCustomerName.setText("");editTextMobileNo.setText("");editTextAddress.setText("");editTextAge.setText("");
//        editTextGender.setText("");
        editTextRESPH.setText("");editTextREAxis.setText("");editTextREVA.setText("");editTextRECYL.setText("");editTextRENearAdd.setText("");
        editTextLESPH.setText("");editTextLEAxis.setText("");editTextLEVA.setText("");editTextLECYL.setText("");
        editTextLENearAdd.setText("");editTextGlass.setText("");editTextFrame.setText("");editTextTotal.setText("");
        editTextAdvance.setText("");editTextBalance.setText("");
    }

    @Override
    public void onNameClicked(SearchCustomerModel searchCustomerModel, int position) {
    }

    public void onGenderItem(int position) {
        genderDialog = new GenderDialog(
                getContext(),
                GenderModel.getGenderModelList(),
                BillingFragment.this,
                position
        );
        genderDialog.show();
    }

    @Override
    public void onGenderClick(GenderModel genderModel,int position) {
        editTextGender.setText(genderModel.getName());
        genderDialog.dismiss();
    }

    public void onLEVAContent(int position) {
                levaDialog = new LEVADialog(
                        getContext(),
                        LEVAModel.getLEVAModelList(),
                        BillingFragment.this,
                        position
                );
                levaDialog.show();
            }

    @Override
    public void onLEAxisClick(LEAxisModel leAxisModel,int position) {
        editTextLEAxis.setText(leAxisModel.getName());
        leAxisDialog.dismiss();
    }

    public void onLEAxisClick(int position) {
                    leAxisDialog = new LEAxisDialog(
                            getContext(),
                            LEAxisModel.getLEAxisModelList(),
                            BillingFragment.this,
                            position
                    );
                leAxisDialog.show();
                }

    @Override
    public void onLEVAClick(LEVAModel levaModel,int position) {
        editTextLEVA.setText(levaModel.getName());
        levaDialog.dismiss();
    }

    @Override
    public void onREAxisClick(REAxisModel leAxisModel, int position) {
        editTextREAxis.setText(leAxisModel.getName());
        reAxisDialog.dismiss();
    }

    @Override
    public void onREVAClick(REVAModel revaModel, int position) {
        editTextREVA.setText(revaModel.getName());
        revaDialog.dismiss();
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
                        editTextDate.setText(simpleDateFormat.format(newDate.getTime()));

                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}