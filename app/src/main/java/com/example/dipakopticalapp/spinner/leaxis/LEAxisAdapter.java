package com.example.dipakopticalapp.spinner.leaxis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;

import java.util.ArrayList;

public class LEAxisAdapter extends RecyclerView.Adapter<LEAxisAdapter.ViewHolder> {
    ArrayList<LEAxisModel>leAxisModels = new ArrayList<>();
    ILEAxisListener ileAxisListener;
    int position;

    public LEAxisAdapter(ArrayList<LEAxisModel> leAxisModels,
                         ILEAxisListener ileAxisListener,
                         int position) {
        this.leAxisModels = leAxisModels;
        this.ileAxisListener = ileAxisListener;
        this.position = position;
    }

    @NonNull
    @Override
    public LEAxisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leaxis_layout,
                parent,false);

        return new LEAxisAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LEAxisAdapter.ViewHolder holder, int position) {
        LEAxisModel leAxisModel = leAxisModels.get(position);
        holder.textViewName.setText(leAxisModel.getName());
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ileAxisListener.onLEAxisClick(leAxisModel,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return leAxisModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView textViewName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.row_leaxis_name);
        }
    }
}
