package com.example.dipakopticalapp.spinner.reaxis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.spinner.leaxis.ILEAxisListener;
import com.example.dipakopticalapp.spinner.leaxis.LEAxisModel;

import java.util.ArrayList;

public class REAxisAdapter extends RecyclerView.Adapter<REAxisAdapter.ViewHolder> {
    ArrayList<REAxisModel> reAxisModels = new ArrayList<>();
    IREAxisListener ireAxisListener;
    int position;

    public REAxisAdapter(ArrayList<REAxisModel> reAxisModels, IREAxisListener ireAxisListener, int position) {
        this.reAxisModels = reAxisModels;
        this.ireAxisListener = ireAxisListener;
        this.position = position;
    }

    @NonNull
    @Override
    public REAxisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leaxis_layout,
                parent,false);

        return new REAxisAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull REAxisAdapter.ViewHolder holder, int position) {
        REAxisModel leAxisModel = reAxisModels.get(position);
        holder.textViewName.setText(leAxisModel.getName());

        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ireAxisListener.onREAxisClick(leAxisModel,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return reAxisModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.row_leaxis_name);
        }
    }
}