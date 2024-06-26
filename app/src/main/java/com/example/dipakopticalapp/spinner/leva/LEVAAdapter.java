package com.example.dipakopticalapp.spinner.leva;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;

import java.util.ArrayList;

public class LEVAAdapter extends RecyclerView.Adapter<LEVAAdapter.ViewHolder> {
    ArrayList<LEVAModel> levaModels=new ArrayList<>();
    ILEVAListener ilevaListener;
    int position;

    public LEVAAdapter(ArrayList<LEVAModel> levaModels, ILEVAListener ilevaListener, int position) {
        this.levaModels = levaModels;
        this.ilevaListener = ilevaListener;
        this.position = position;
    }

    @NonNull
    @Override
    public LEVAAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leva_layout,
                parent,false);

        return new LEVAAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LEVAAdapter.ViewHolder holder, int position) {
        LEVAModel levaModel = levaModels.get(position);
        holder.textViewName.setText(levaModel.getName());
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ilevaListener.onLEVAClick(levaModel,position);
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
