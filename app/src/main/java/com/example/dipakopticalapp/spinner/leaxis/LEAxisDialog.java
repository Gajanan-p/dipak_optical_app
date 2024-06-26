package com.example.dipakopticalapp.spinner.leaxis;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.leva.LEVAAdapter;

import java.util.ArrayList;

public class LEAxisDialog extends Dialog {
    ArrayList<LEAxisModel> leAxisModels;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ILEAxisListener ileAxisListener;
    int position;

    public LEAxisDialog(@NonNull Context context,
                        ArrayList<LEAxisModel> leAxisModels,
                        ILEAxisListener ileAxisListener,
                        int position) {
        super(context);
        this.leAxisModels = leAxisModels;
        this.ileAxisListener = ileAxisListener;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.row_dialog_leaxis);
        adapter = new LEAxisAdapter(leAxisModels,ileAxisListener,position);
        recyclerView = findViewById(R.id.list_le_axis);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
