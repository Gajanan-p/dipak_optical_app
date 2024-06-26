package com.example.dipakopticalapp.spinner.reva;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.leva.ILEVAListener;
import com.example.dipakopticalapp.spinner.leva.LEVAAdapter;
import com.example.dipakopticalapp.spinner.leva.LEVAModel;

import java.util.ArrayList;

public class REVADialog extends Dialog {
    ArrayList<REVAModel> revaModels;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    IREVAListener irevaListener;
    int position;

    public REVADialog(@NonNull Context context,
                      ArrayList<REVAModel> revaModels,
                      IREVAListener irevaListener,
                      int position)
    {
        super(context);
        this.revaModels = revaModels;
        this.irevaListener = irevaListener;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.row_dialog_leva);
        adapter = new REVAAdapter(revaModels,irevaListener,position);
        recyclerView = findViewById(R.id.list_leva);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
