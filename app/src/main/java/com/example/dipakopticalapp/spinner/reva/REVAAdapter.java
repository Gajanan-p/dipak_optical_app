package com.example.dipakopticalapp.spinner.reva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.leva.ILEVAListener;
import com.example.dipakopticalapp.spinner.leva.LEVAModel;

import java.util.ArrayList;

public class REVAAdapter extends RecyclerView.Adapter<REVAAdapter.ViewHolder> {
    ArrayList<REVAModel> levaModels=new ArrayList<>();
    IREVAListener ilevaListener;
    int position;

    public REVAAdapter(ArrayList<REVAModel> levaModels,
                       IREVAListener ilevaListener,
                       int position)
    {
        this.levaModels = levaModels;
        this.ilevaListener = ilevaListener;
        this.position = position;
    }

    @NonNull
    @Override
    public REVAAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leva_layout,
                parent,false);

        return new REVAAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull REVAAdapter.ViewHolder holder, int position) {
        REVAModel levaModel = levaModels.get(position);
        holder.textViewName.setText(levaModel.getName());
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilevaListener.onREVAClick(levaModel,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return levaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.row_leva_name);
        }
    }
}
