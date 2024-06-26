package com.example.dipakopticalapp.spinner.leva;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.gender.GenderAdapter;
import com.example.dipakopticalapp.spinner.gender.GenderModel;
import com.example.dipakopticalapp.spinner.gender.IGenderListener;

import java.util.ArrayList;

public class LEVADialog extends Dialog {
    ArrayList<LEVAModel> levaModels;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ILEVAListener ilevaListener;
    int position;

    public LEVADialog(@NonNull Context context,
                        ArrayList<LEVAModel> levaModels,
                        ILEVAListener ilevaListener, int position) {
        super(context);
        this.levaModels = levaModels;
        this.ilevaListener = ilevaListener;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.row_dialog_leva);
        adapter = new LEVAAdapter(levaModels,ilevaListener,position);
        recyclerView = findViewById(R.id.list_leva);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
