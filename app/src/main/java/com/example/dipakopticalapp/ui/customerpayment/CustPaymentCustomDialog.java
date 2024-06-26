package com.example.dipakopticalapp.ui.customerpayment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.ui.bill.CustomListener;
import com.example.dipakopticalapp.ui.bill.SearchNameAdapter;
import com.example.dipakopticalapp.ui.billlist.CustomAdapter;
import com.example.dipakopticalapp.ui.billlist.CustomItemClickListener;
import com.example.dipakopticalapp.webservices.billlist.BillListModel;
import com.example.dipakopticalapp.webservices.customer.SearchCustomerModel;

import java.util.ArrayList;

public class CustPaymentCustomDialog extends Dialog implements CustomListener {

    ArrayList<SearchCustomerModel> models;
    RecyclerView recyclerView;
    SearchNameAdapter adapter;
    CustomListener customItemClickListener;
    AppCompatEditText editTextSearchName;

    public CustPaymentCustomDialog(@NonNull Context context, ArrayList<SearchCustomerModel> models,
                                   CustomListener customItemClickListener)
    {
        super(context);
        this.models = models;
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_bill_list);
        recyclerView = (RecyclerView) findViewById(R.id.list_item_bill_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        editTextSearchName = (AppCompatEditText) findViewById(R.id.edit_text_item);
        openList();
        editTextSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                adapter.getFilter().filter(arg0);
            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });
    }

    public void openList() {
        adapter = new SearchNameAdapter(getContext(),models,customItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNameClicked(SearchCustomerModel searchCustomerModel, int position) {

    }
}
