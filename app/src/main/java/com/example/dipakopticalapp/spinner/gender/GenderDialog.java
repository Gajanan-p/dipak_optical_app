package com.example.dipakopticalapp.spinner.gender;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dipakopticalapp.R;
import java.util.ArrayList;

public class GenderDialog extends Dialog {
    ArrayList<GenderModel> genderModels;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    IGenderListener iGenderListener;
    int position;

    public GenderDialog(@NonNull Context context,
                        ArrayList<GenderModel> genderModels,
                        IGenderListener iGenderListener, int position) {
        super(context);
        this.genderModels = genderModels;
        this.iGenderListener = iGenderListener;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.row_dialod_gender);
        adapter = new GenderAdapter(genderModels,iGenderListener,position);
        recyclerView = findViewById(R.id.list_gender);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
