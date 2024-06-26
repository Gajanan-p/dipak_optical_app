package com.example.dipakopticalapp.ui.billlist;


import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
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
import com.example.dipakopticalapp.ui.bill.BillingFragment;
import com.example.dipakopticalapp.webservices.bill.ReqViewBillModel;
import com.example.dipakopticalapp.webservices.bill.SaveBillModel;
import com.example.dipakopticalapp.webservices.bill.ViewBillModel;
import com.example.dipakopticalapp.webservices.billlist.BillListModel;
import com.example.dipakopticalapp.webservices.Webservice;
import com.example.dipakopticalapp.webservices.billlist.DeleteBillModel;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillListFragment extends Fragment implements
        View.OnClickListener, ILEVAListener, IGenderListener,
        ILEAxisListener, IREVAListener, IREAxisListener, IBillList,
        CustomItemClickListener{

   // private BillListViewModel mViewModel;
    NavController navController;
    AppCompatButton buttonNewBill;
    AppCompatButton buttonExportExcel;
    AppCompatButton buttonCancel, buttonBillEditCancel;
    AppCompatTextView editTextSearchBill;
    RecyclerView recyclerView;
    private ArrayList<BillListModel>billListModels;
    BillListAdapter billListAdapter;
    BillListModel billListModel;
    LinearLayout containerBillList;
    LinearLayout containerBillListView;
    AppCompatEditText editTextCustomerName,editTextMobileNo,editTextAddress,editTextAge;
    AppCompatEditText editTextRESPH,editTextRECYL,editTextRENearAdd,editTextLESPH,editTextLECYL,editTextLENearAdd,
            editTextGlass,editTextFrame,editTextTotal,editTextAdvance,editTextBalance,editTextGlassPrice,editTextFramePrice;
    AppCompatButton buttonViewSubmit,buttonViewCancel;
    AppCompatTextView editTextGender,editTextLEAxis,editTextLEVA,editTextREAxis,editTextREVA,editTextDate;
    SimpleDateFormat simpleDateFormat;
    boolean updateFlag=false;
    ArrayList<BillListModel>tempBillListModelFinal = new ArrayList<>();
    ArrayList<BillListModel>tempDialogBillList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private ArrayList<ViewBillModel>viewBillModels;
    CustomDialog customDialog;
    Calendar calendar;
    private GenderDialog genderDialog;
    private LEVADialog levaDialog;
    private LEAxisDialog leAxisDialog;
    private REAxisDialog reAxisDialog;
    private REVADialog revaDialog;
    public static BillListFragment newInstance() {
        return new BillListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_list_fragment, container, false);

        editTextSearchBill = view.findViewById(R.id.edit_bill_list_search_bill);
        editTextSearchBill.setOnClickListener(this);
        buttonNewBill = view.findViewById(R.id.button_bill_list_new_bill);
        buttonNewBill.setOnClickListener(this);
        buttonExportExcel = view.findViewById(R.id.button_bill_list_excel);
        buttonCancel = view.findViewById(R.id.button_bill_list_cancel);
        buttonCancel.setOnClickListener(this);
        buttonBillEditCancel = view.findViewById(R.id.button_bill_cancel);
        buttonBillEditCancel.setOnClickListener(this);
        containerBillList = view.findViewById(R.id.linear_bill_list);
        containerBillListView = view.findViewById(R.id.linear_bill_list_view);
        recyclerView =(RecyclerView) view.findViewById(R.id.recycle_list_bill_list);
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
        buttonViewSubmit = view.findViewById(R.id.button_bill_submit);
        buttonViewSubmit.setOnClickListener(this);
        buttonViewCancel = view.findViewById(R.id.button_bill_cancel);
        editTextCustomerName = view.findViewById(R.id.edit_bill_customer_name);
        editTextCustomerName.setOnClickListener(this);
        buttonExportExcel.setOnClickListener(this);
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy  hh:mm");
        String currentDate = simpleDateFormat.format(calendar.getTime());
        editTextDate.setText(currentDate);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
    }

    public void onNewBillButtonClicked() {
        navController.navigate(R.id.action_nav_billListFragment_to_nav_billingFragment);
    }

    public void onBillListClicked(){
        containerBillList.setVisibility(View.VISIBLE);
        containerBillListView.setVisibility(View.GONE);
    }

    public void onBillListViewClicked(){
        containerBillList.setVisibility(View.GONE);
        containerBillListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getBillList();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_bill_list_new_bill:{
                onNewBillButtonClicked();
                break;
            }
            case R.id.edit_bill_list_search_bill:{
                openBillListDialog();
                break;
            }
            case R.id.button_bill_submit:{
                createBillData();
                break;
            }
            case R.id.button_bill_cancel:{
                onBillListClicked();
                break;
            }
            case R.id.button_bill_list_cancel:{
                clearDataBillList();
                break;
            }
            case R.id.button_bill_list_excel:{
                createExcelSheet();
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
                BillListFragment.this,
                position
        );
        reAxisDialog.show();

    }

    public void onREVAContent(int position){
        revaDialog = new REVADialog(
                getContext(),
                REVAModel.getREVAModelList(),
                BillListFragment.this,
                position
        );
        revaDialog.show();
    }
    private void clearDataBillList() {
        editTextSearchBill.setText("");
    }

    public void getBillList(){
        progressDialog = createProgressDialog(getContext());
        Call<ArrayList<BillListModel>> arrayListCall= Webservice
                .getBillListData()
                .getBillListModel();
        arrayListCall.enqueue(new Callback<ArrayList<BillListModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BillListModel>> call, Response<ArrayList<BillListModel>> response) {
                billListModels =response.body();
                if (billListModels.size()>0){
                    tempBillListModelFinal.addAll(response.body());
                    tempDialogBillList.addAll(response.body());
                    updateBillList();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<ArrayList<BillListModel>> call, Throwable t) {
                Log.i("error",t.getMessage());
            }
        });
    }

    public void openBillListDialog(){
        customDialog = new CustomDialog(getContext(),
                billListModels,
                BillListFragment.this);
        customDialog.show();
    }

    @Override
    public void onItemClick(BillListModel model, int position) {
        editTextSearchBill.setText(model.getName());
        customDialog.dismiss();
        shortListBillList(model,position);
    }

    private void updateBillList() {
        if (billListModels!=null){
            billListAdapter = new BillListAdapter(billListModels,this);
            recyclerView.setAdapter(billListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onBillListView(BillListModel billListModel) {
        this.billListModel = billListModel;
        viewBillList();
        onBillListViewClicked();
    }


    @Override
    public void onBillListDelete(BillListModel billListModel) {
        this.billListModel = billListModel;
        DeleteBillModel deleteBillModel = new DeleteBillModel();
        deleteBillModel.setBillId(billListModel.getBillId().toString());
        deleteBillList(deleteBillModel);
    }

    @Override
    public void onBillListEdit(BillListModel billListModel) {
        this.billListModel = billListModel;
        viewBillList();
        onBillListViewClicked();
    }

    public void deleteBillList(DeleteBillModel deleteBillModel) {
        progressDialog = createProgressDialog(getContext());
        deleteBillModel.setBillId(billListModel.getBillId().toString());

        Call<DeleteBillModel> deleteBillModelCall = Webservice
                .getBillListData()
                .setDeleteBillModel(deleteBillModel);
        deleteBillModelCall.enqueue(new Callback<DeleteBillModel>() {
            @Override
            public void onResponse(Call<DeleteBillModel> call, Response<DeleteBillModel> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                    getBillList();
                    billListAdapter.notifyDataSetChanged();
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("")
                            .setContentText(getString(R.string.delete_record))
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    /*Posted value*/
//                                    Bundle bundle = get().getExtras();
//                                    String suggestionVal = bundle.getString("suggestionVal");
//                                    finish();

                                }
                            }).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DeleteBillModel> call, Throwable t) {

            }
        });

    }
    public void viewBillList(){
        progressDialog = createProgressDialog(getContext());
        ReqViewBillModel reqViewBillModel = new ReqViewBillModel();

        reqViewBillModel.setBillId(billListModel.getBillId());
        Call<ArrayList<ViewBillModel>> viewArrayListCall = Webservice
                .setBillData()
                .setViewBillModel(reqViewBillModel);
        viewArrayListCall.enqueue(new Callback<ArrayList<ViewBillModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ViewBillModel>> call, Response<ArrayList<ViewBillModel>> response) {
                viewBillModels = response.body();
                changeDataUI();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<ViewBillModel>> call, Throwable t) {

            }
        });
    }

    void shortListBillList(BillListModel billListModel,int position){
        Log.i("BillList",String.valueOf(billListModel.getBillId()));
        Log.i("BillList",String.valueOf(billListModel.getName()));
        shortHeldStockCorrectionList(billListModel);
    }

    void shortHeldStockCorrectionList(BillListModel billListModel){
        billListModels.clear();
        for(int i=0;i<tempBillListModelFinal.size();i++){
            if(tempBillListModelFinal
                    .get(i)
                    .getName().equals(
                            (billListModel.getName())
                    )){
                billListModels.add(tempBillListModelFinal.get(i));
            }
        }
        updateBillList();// heldStockCorrectionAdapter.notifyDataSetChanged();
    }

    public void changeDataUI() {
        updateFlag = true;
        ViewBillModel viewBillModel1 = viewBillModels.get(0);
        editTextCustomerName.setText(viewBillModel1.getName());
        editTextMobileNo.setText(viewBillModel1.getMobile());
        editTextAddress.setText(viewBillModel1.getAddress());
        editTextDate.setText(viewBillModel1.getDate());
        editTextAge.setText(viewBillModel1.getAge());
        editTextGender.setText(viewBillModel1.getGender());
        editTextRESPH.setText(viewBillModel1.getREDistSPH());
        editTextREAxis.setText(viewBillModel1.getREDistAXIS());
        editTextREVA.setText(viewBillModel1.getREDistVA());
        editTextRECYL.setText(viewBillModel1.getREDistCYL());
        editTextRENearAdd.setText(viewBillModel1.getRENearADD());
        editTextLESPH.setText(viewBillModel1.getLEDistSPH());
        editTextLEAxis.setText(viewBillModel1.getLEDistAXIS());
        editTextLEVA.setText(viewBillModel1.getLEDistVA());
        editTextLECYL.setText(viewBillModel1.getLEDistCYL());
        editTextLENearAdd.setText(viewBillModel1.getLENearADD());
        editTextGlass.setText(viewBillModel1.getGlass());
        editTextFrame.setText(viewBillModel1.getFrame());
        editTextTotal.setText(viewBillModel1.getTotal());
        editTextAdvance.setText(viewBillModel1.getAdvance());
        editTextBalance.setText(viewBillModel1.getBalance());

    }

    public void createBillData(){
        progressDialog = createProgressDialog(getContext());
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
        saveBillModel.setGender(editTextGender.getText().toString());
        saveBillModel.setBillId("0");
        Call<SaveBillModel> saveBillModelCall = Webservice
                .setBillData()
                .setSaveBillModel(saveBillModel);
        saveBillModelCall.enqueue(new Callback<SaveBillModel>() {
            @Override
            public void onResponse(Call<SaveBillModel> call, Response<SaveBillModel> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(getContext(),"Data Save Successfully",Toast.LENGTH_LONG).show();
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE).setTitleText("Data Save Successfully!").show();

                    clearData();
                    onBillListClicked();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SaveBillModel> call, Throwable t) {
                Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void clearData(){
        editTextCustomerName.setText("");editTextMobileNo.setText("");editTextAddress.setText("");editTextAge.setText("");editTextGender.setText("");editTextRESPH.setText("");editTextREAxis.setText("");
        editTextREVA.setText("");editTextRECYL.setText("");editTextRENearAdd.setText("");editTextLESPH.setText("");editTextLEAxis.setText("");editTextLEVA.setText("");
        editTextLECYL.setText("");editTextLENearAdd.setText("");editTextGlass.setText("");editTextFrame.setText("");editTextTotal.setText("");editTextAdvance.setText("");editTextBalance.setText("");
    }

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
        cell.setCellValue("Bill No.");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(1);
        cell.setCellValue("Date");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(2);
        cell.setCellValue("Name");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(3);
        cell.setCellValue("Mobile No.");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(4);
        cell.setCellValue("Amount");
        cell.setCellStyle(cellStyle);
        cell=row.createCell(5);
        cell.setCellValue("Paid");
        cell.setCellStyle(cellStyle);

        sheet.setColumnWidth(0,(10*100));
        sheet.setColumnWidth(1,(10*300));
        sheet.setColumnWidth(2,(10*400));
        sheet.setColumnWidth(3,(10*400));
        sheet.setColumnWidth(4,(10*300));
        sheet.setColumnWidth(5,(10*300));
        for (int i=0; i<billListModels.size();i++){
            // Create a New Row for every new entry in list
            Row rowData = sheet.createRow(i + 1);
            // Create Cells for each row
            cell = rowData.createCell(0);
            cell.setCellValue(billListModels.get(i).getBillId());
            cell = rowData.createCell(1);
            cell.setCellValue(billListModels.get(i).getDate());
            cell = rowData.createCell(2);
            cell.setCellValue(billListModels.get(i).getName());
            cell = rowData.createCell(3);
            cell.setCellValue(billListModels.get(i).getMobile());
            cell = rowData.createCell(4);
            cell.setCellValue(billListModels.get(i).getTotal());
            cell = rowData.createCell(5);
            cell.setCellValue(billListModels.get(i).getAdvance());

        }

        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String currentDate = dateFormat.format(calendar.getTime());
        File file = new File(path,currentDate+"BillList.xls");
        FileOutputStream outputStream =null;

        try {
            outputStream=new FileOutputStream(file);
            wb.write(outputStream);
            new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE).setTitleText("Data Exported For Download Folder!").show();
            progressDialog.dismiss();
        } catch (java.io.IOException e) {
            e.printStackTrace();

            Toast.makeText(getContext(),"Excel Not Created Successfully",Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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


    public void onGenderItem(int position) {
        genderDialog = new GenderDialog(
                getContext(),
                GenderModel.getGenderModelList(),
                BillListFragment.this,
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
                BillListFragment.this,
                position
        );
        levaDialog.show();
    }

    @Override
    public void onLEAxisClick(LEAxisModel leAxisModel, int position) {
        editTextLEAxis.setText(leAxisModel.getName());
        leAxisDialog.dismiss();
    }

    public void onLEAxisClick(int position) {

        leAxisDialog = new LEAxisDialog(
                getContext(),
                LEAxisModel.getLEAxisModelList(),
                BillListFragment.this,
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