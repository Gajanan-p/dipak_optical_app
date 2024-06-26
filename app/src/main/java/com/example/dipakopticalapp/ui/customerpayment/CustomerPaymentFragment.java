package com.example.dipakopticalapp.ui.customerpayment;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.ui.bill.CustomListener;
import com.example.dipakopticalapp.ui.utils.AppPreference;
import com.example.dipakopticalapp.webservices.customer.SearchCustomerModel;
import com.example.dipakopticalapp.webservices.customerpayment.CustomerPaymentListModel;
import com.example.dipakopticalapp.webservices.customerpayment.DeletePayment;
import com.example.dipakopticalapp.webservices.customerpayment.GetPendingByCustIdModel;
import com.example.dipakopticalapp.webservices.customerpayment.RequestGetPendingByCustId;
import com.example.dipakopticalapp.webservices.customerpayment.SaveCustPaymentModel;
import com.example.dipakopticalapp.webservices.Webservice;
import com.example.dipakopticalapp.webservices.customerpayment.UpdateCustPaymentModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerPaymentFragment extends Fragment implements
        View.OnClickListener,
        CustomListener,
        ICustomerList
       {
    AppCompatTextView appCompatTextViewCustomerName;
    AppCompatEditText appCompatEditTextDate,appCompatEditTextBalance,appCompatEditTextPayed;
    AppCompatButton appCompatButtonCancel,appCompatButtonSave,appCompatButtonExport,appCompatButtonUpdate;
    LinearLayout linearLayoutButtonSave;
    LinearLayout linearLayoutButtonUpdate;
    private ArrayList<SearchCustomerModel> searchCustomerModels;
    private ArrayList<CustomerPaymentListModel> customerPaymentListModels;
    ArrayList<CustomerPaymentListModel> updateCustomerPaymentListFinal = new ArrayList<>();
    private ProgressDialog progressDialog;
    RecyclerView recyclerViewCustomerList;
    ArrayList<GetPendingByCustIdModel> getPendingByCustIdModels;
    CustomerListAdapter customerListAdapter;
    CustomerPaymentListModel customerPaymentListModel;
    CustPaymentCustomDialog dialog;
    Calendar calendar;
    boolean updateFlag=false;
   // WritableWorkbook workbook;
    public static CustomerPaymentFragment newInstance() {
        return new CustomerPaymentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.customer_payment_fragment, container, false);

        appCompatTextViewCustomerName = view.findViewById(R.id.text_customer_payment_customer_name);
        appCompatTextViewCustomerName.setOnClickListener(this);

        appCompatButtonSave = view.findViewById(R.id.button_customer_payment_save);
        appCompatButtonSave.setOnClickListener(this);

        appCompatButtonExport = view.findViewById(R.id.button_customer_payment_export);
        appCompatButtonCancel = view.findViewById(R.id.button_customer_payment_cancel);

        appCompatEditTextDate = view.findViewById(R.id.text_customer_payment_date);
        appCompatEditTextBalance = view.findViewById(R.id.text_customer_payment_balance);
        appCompatEditTextPayed = view.findViewById(R.id.text__customer_payment_payed);

        appCompatButtonUpdate = view.findViewById(R.id.button_customer_payment_update);
        appCompatButtonUpdate.setOnClickListener(this);
        linearLayoutButtonSave = view.findViewById(R.id.linear_customer_payment_save);
        linearLayoutButtonUpdate = view.findViewById(R.id.linear_customer_payment_update);
        calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
        appCompatEditTextDate.setText(currentDate);

        recyclerViewCustomerList = (RecyclerView) view.findViewById(R.id.recycle_list_customer_payment);
        appCompatButtonExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerPaymentListModels.size()>0) {
                   createExcelSheet();
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  mViewModel = new ViewModelProvider(this).get(CustomerPaymentViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
        getCustomerList();
    }
    public void onSaveButtonClicked(){
        linearLayoutButtonSave.setVisibility(View.VISIBLE);
        linearLayoutButtonUpdate.setVisibility(View.GONE);
    }
    public void onUpdateButtonClicked(){
        linearLayoutButtonSave.setVisibility(View.GONE);
        linearLayoutButtonUpdate.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_customer_payment_customer_name:{
                openCustomerListDialog();
                break;
            }
            case R.id.button_customer_payment_save:{
                createCustomerPayment();
                break;
            }
            case R.id.button_customer_payment_update:{
                updateCustomerNamePaymentList();
                break;
            }
            case R.id.button_customer_payment_cancel:{
                clearData();
                break;
            }
            case R.id.button_customer_payment_export:{
                createExcelSheet();
                break;
            }
        }
    }

    public void openCustomerListDialog() {
        //   progressDialog = createProgressDialog(getContext());
        Call<ArrayList<SearchCustomerModel>> arrayListCall = Webservice
                .getSearchCustomerData()
                .getSearchCustomerModel();
        arrayListCall.enqueue(new Callback<ArrayList<SearchCustomerModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchCustomerModel>> call, Response<ArrayList<SearchCustomerModel>> response) {
                searchCustomerModels = response.body();
                if (searchCustomerModels!=null){
                    dialog = new CustPaymentCustomDialog(getContext(),
                            searchCustomerModels,
                            CustomerPaymentFragment.this);
                    dialog.show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchCustomerModel>> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }

    @Override
    public void onNameClicked(SearchCustomerModel searchCustomerModel, int position) {
        AppPreference.setSearchCustomerDataPreferences(getContext(),searchCustomerModel);
        shortCustomerNameList(searchCustomerModel,position);
        appCompatTextViewCustomerName.setText(searchCustomerModel.getName());
       if (dialog!=null) {
          dialog.dismiss();
       }
        getGetPendingByCustId();
    }

    public void createCustomerPayment(){
        progressDialog = createProgressDialog(getContext());
        SaveCustPaymentModel saveCustPaymentModel = new SaveCustPaymentModel();
        SearchCustomerModel searchCustomerModel = AppPreference.getSearchCustomerDataPreferences(getContext());
        saveCustPaymentModel.setCustomerId(searchCustomerModel.getCustomerId().toString());
        saveCustPaymentModel.setDate(appCompatEditTextDate.getText().toString());
        saveCustPaymentModel.setBalance(appCompatEditTextBalance.getText().toString());
        saveCustPaymentModel.setAdvance(appCompatEditTextPayed.getText().toString());

        Call<SaveCustPaymentModel>arrayListCall = Webservice
                .getCustomerPaymentData()
                .setSaveCustPaymentModel(saveCustPaymentModel);
        arrayListCall.enqueue(new Callback<SaveCustPaymentModel>() {
            @Override
            public void onResponse(Call<SaveCustPaymentModel> call, Response<SaveCustPaymentModel> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(),"Save Successfully",Toast.LENGTH_LONG).show();
                    getCustomerList();
                    clearData();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<SaveCustPaymentModel> call, Throwable t) {
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCustomerList() {
        progressDialog = createProgressDialog(getContext());
        Call<ArrayList<CustomerPaymentListModel>> arrayListCall= Webservice
                .getCustomerPaymentData()
                .getCustomerPaymentListModel();
        arrayListCall.enqueue(new Callback<ArrayList<CustomerPaymentListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CustomerPaymentListModel>> call,
                                   Response<ArrayList<CustomerPaymentListModel>> response) {
                customerPaymentListModels = response.body();
                if (customerPaymentListModels.size()>0){
                    updateCustomerPaymentList(customerPaymentListModels);
                    updateCustomerPaymentListFinal.addAll(response.body());
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<ArrayList<CustomerPaymentListModel>> call, Throwable t) {
                Log.e("Error from server", t.getMessage());
            }
        });
    }

    private void updateCustomerPaymentList(ArrayList<CustomerPaymentListModel> customerPaymentListModels) {
        if (customerPaymentListModels!=null) {
            customerListAdapter = new CustomerListAdapter(customerPaymentListModels,this);
            recyclerViewCustomerList.setAdapter(customerListAdapter);
            recyclerViewCustomerList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        if(customerPaymentListModels.size()>0){}
        else{
            Toast.makeText(getContext(),"This customer are not available in customer list !",Toast.LENGTH_LONG).show();//No Record Found!
        }
    }

    @Override
    public void onCustomerListEdit(CustomerPaymentListModel customerPaymentListModel) {
        //changeDataUI(customerPaymentListModel);
    }

    @Override
    public void onCustomerListDelete(CustomerPaymentListModel customerPaymentListModel) {
        this.customerPaymentListModel=customerPaymentListModel;
        DeletePayment deletePayment = new DeletePayment();
        deletePayment.setCustomerPayId(customerPaymentListModel.getCustomerPayId().toString());
        deleteCustomerPaymentList(deletePayment);
    }

    public void clearData(){
        appCompatTextViewCustomerName.setText("");
        appCompatEditTextBalance.setText("");
        appCompatEditTextPayed.setText("");
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

    public void deleteCustomerPaymentList(DeletePayment deletePayment){
        progressDialog = createProgressDialog(getContext());
        deletePayment.setCustomerPayId(customerPaymentListModel.getCustomerPayId().toString());

        Call<DeletePayment> deletePaymentCall = Webservice
                .getCustomerPaymentData()
                .setDeletePayment(deletePayment);
        deletePaymentCall.enqueue(new Callback<DeletePayment>() {
            @Override
            public void onResponse(Call<DeletePayment> call, Response<DeletePayment> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(),"Delete Successfully",Toast.LENGTH_LONG).show();
                  //  getCustomerList();
                    customerListAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<DeletePayment> call, Throwable t) {
                Log.e("Error from server", t.getMessage());
            }
        });
    }

    public void updateCustomerNamePaymentList(){
        progressDialog = createProgressDialog(getContext());
        UpdateCustPaymentModel updateCustPaymentModel = new UpdateCustPaymentModel();
        SearchCustomerModel searchCustomerModel = AppPreference.getSearchCustomerDataPreferences(getContext());
        updateCustPaymentModel.setCustomerPayId(customerPaymentListModel.getCustomerPayId().toString());
        updateCustPaymentModel.setCustomerId(searchCustomerModel.getCustomerId().toString());
        updateCustPaymentModel.setDate(appCompatEditTextDate.getText().toString());
        updateCustPaymentModel.setTotal(appCompatEditTextBalance.getText().toString());
        updateCustPaymentModel.setAdvance(appCompatEditTextPayed.getText().toString());

        Call<UpdateCustPaymentModel>updateCustPaymentModelCall = Webservice
                .getCustomerPaymentData()
                .setUpdateCustPaymentModel(updateCustPaymentModel);
        updateCustPaymentModelCall.enqueue(new Callback<UpdateCustPaymentModel>() {
            @Override
            public void onResponse(Call<UpdateCustPaymentModel> call, Response<UpdateCustPaymentModel> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(),"Updated Successfully",Toast.LENGTH_LONG).show();
                    clearData();
                progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UpdateCustPaymentModel> call, Throwable t) {

            }
        });
    }

    public void getGetPendingByCustId(){
        progressDialog=createProgressDialog(getContext());
        RequestGetPendingByCustId requestGetPendingByCustId = new RequestGetPendingByCustId();
        SearchCustomerModel searchCustomerModel = AppPreference.getSearchCustomerDataPreferences(getContext());

        requestGetPendingByCustId.setCustomerId(searchCustomerModel.getCustomerId());
        Call<ArrayList<GetPendingByCustIdModel>> arrayListCall = Webservice
                .getCustomerPaymentData()
                .setGetPendingByCustId(requestGetPendingByCustId);
        arrayListCall.enqueue(new Callback<ArrayList<GetPendingByCustIdModel>>() {
            @Override
            public void onResponse(Call<ArrayList<GetPendingByCustIdModel>> call, Response<ArrayList<GetPendingByCustIdModel>> response) {
                getPendingByCustIdModels = response.body();
                setBalance(getPendingByCustIdModels);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<GetPendingByCustIdModel>> call, Throwable t) {
                Log.e("Error from server", t.getMessage());
            }
        });
    }

    public void setBalance(ArrayList<GetPendingByCustIdModel> getPendingByCustIdModels){
        GetPendingByCustIdModel getPendingByCustIdModel = getPendingByCustIdModels.get(0);
        appCompatEditTextBalance.setText(getPendingByCustIdModel.getBalance());
    }

//    public void changeDataUI(CustomerPaymentListModel customerPaymentListModel) {
//        updateFlag = true;
//        this.customerPaymentListModel = customerPaymentListModel;
//        appCompatTextViewCustomerName.setText(customerPaymentListModel.getName());
//        appCompatEditTextDate.setText(customerPaymentListModel.getDate());
//        appCompatEditTextBalance.setText(customerPaymentListModel.getBalance());
//        appCompatEditTextPayed.setText(customerPaymentListModel.getAdvance());
//        onUpdateButtonClicked();
//    }

    private void createExcelSheet() {
        progressDialog = createProgressDialog(getContext());
        Workbook wb=new HSSFWorkbook();
        Cell cell=null;
        CellStyle cellStyle=wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setColor(HSSFColor.WHITE.index);
        cellStyle.setFont(font);
        //Now we are creating sheet
        Sheet sheet=null;
        sheet = wb.createSheet("Name of sheet");
        //Now column and row
        Row row =sheet.createRow(0);

               cell=row.createCell(0);
               cell.setCellValue("Date");
               cell.setCellStyle(cellStyle);
               cell=row.createCell(1);
               cell.setCellValue("Name");
               cell.setCellStyle(cellStyle);
               cell=row.createCell(2);
               cell.setCellValue("Mobile No.");
               cell.setCellStyle(cellStyle);
               cell=row.createCell(3);
               cell.setCellValue("Paid");
               cell.setCellStyle(cellStyle);


        sheet.setColumnWidth(0,(10*400));
        sheet.setColumnWidth(1,(10*400));
        sheet.setColumnWidth(2,(10*400));
        sheet.setColumnWidth(3,(10*300));
               for (int i=0; i<customerPaymentListModels.size();i++){
                   // Create a New Row for every new entry in list
                   Row rowData = sheet.createRow(i + 1);
                   // Create Cells for each row
                   cell = rowData.createCell(0);
                   cell.setCellValue(customerPaymentListModels.get(i).getDate());
                   cell = rowData.createCell(1);
                   cell.setCellValue(customerPaymentListModels.get(i).getName());
                   cell = rowData.createCell(2);
                   cell.setCellValue(customerPaymentListModels.get(i).getMobile());
                   cell = rowData.createCell(3);
                   cell.setCellValue(customerPaymentListModels.get(i).getAdvance());

               }

               String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
               String currentDate = dateFormat.format(calendar.getTime());
               File file = new File(path,currentDate+"custpay.xls");
               FileOutputStream outputStream =null;

               Uri uri = Uri.fromFile(file);

               try {
                   outputStream=new FileOutputStream(file);
                   wb.write(outputStream);
                   Toast.makeText(getContext(),"Excel Created Successfully",Toast.LENGTH_LONG).show();
                   progressDialog.dismiss();
               } catch (java.io.IOException e) {
                   e.printStackTrace();

                   Toast.makeText(getContext(),"Excel Not Created Successfully",Toast.LENGTH_LONG).show();
                   try {
                       outputStream.close();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   }
               }

    }
    public void shortCustomerNameList(SearchCustomerModel searchCustomerModel,int position){
        Log.i("CustomerPayment",String.valueOf(searchCustomerModel.getName()));
        Log.i("CustomerPayment",String.valueOf(searchCustomerModel.getCustomerId()));

        shortCustomerPaymentList(searchCustomerModel);
    }

    private void shortCustomerPaymentList(SearchCustomerModel searchCustomerModel) {
        customerPaymentListModels.clear();
        for(int i=0;i<updateCustomerPaymentListFinal.size();i++){
            if(updateCustomerPaymentListFinal
                    .get(i)
                    .getName().equals(
                            (searchCustomerModel.getName())
                    )){
                customerPaymentListModels.add(updateCustomerPaymentListFinal.get(i));
            }
        }

        if (updateCustomerPaymentListFinal!=null) {
            updateCustomerPaymentList(customerPaymentListModels);// heldStockCorrectionAdapter.notifyDataSetChanged();
        }else
        {
            Toast.makeText(getContext(),"No Record Found!",Toast.LENGTH_LONG).show();
        }
    }

}
