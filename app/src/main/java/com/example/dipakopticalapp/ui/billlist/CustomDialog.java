package com.example.dipakopticalapp.ui.billlist;

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
import com.example.dipakopticalapp.ui.bill.SearchNameAdapter;
import com.example.dipakopticalapp.webservices.billlist.BillListModel;

import java.util.ArrayList;

public class CustomDialog extends Dialog implements CustomItemClickListener{

    ArrayList<BillListModel> models;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    CustomItemClickListener customItemClickListener;
    AppCompatEditText editTextSearchName;

    public CustomDialog(@NonNull Context context, ArrayList<BillListModel> models,
                        CustomItemClickListener customItemClickListener)
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

    @Override
    public void onItemClick(BillListModel model, int position) {

    }
    public void openList() {
        adapter = new CustomAdapter(
                getContext(),models,customItemClickListener);
        recyclerView.setAdapter(adapter);
    }

}
