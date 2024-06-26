package com.example.dipakopticalapp.spinner.reaxis;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.leaxis.ILEAxisListener;
import com.example.dipakopticalapp.spinner.leaxis.LEAxisAdapter;
import com.example.dipakopticalapp.spinner.leaxis.LEAxisModel;

import java.util.ArrayList;

public class REAxisDialog extends Dialog {

    ArrayList<REAxisModel> leAxisModels;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    IREAxisListener ileAxisListener;
    int position;

    public REAxisDialog(@NonNull Context context,
                        ArrayList<REAxisModel> leAxisModels,
                        IREAxisListener ileAxisListener, int position)
    {
        super(context);
        this.leAxisModels = leAxisModels;
        this.ileAxisListener = ileAxisListener;
        this.position = position;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.row_dialog_leaxis);
        adapter = new REAxisAdapter(leAxisModels,ileAxisListener,position);
        recyclerView = findViewById(R.id.list_le_axis);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
