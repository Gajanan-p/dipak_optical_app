package com.example.dipakopticalapp.ui.billlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.webservices.bill.ViewBillModel;
import com.example.dipakopticalapp.webservices.billlist.BillListModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder>{
    ArrayList<BillListModel> billListModels;
    IBillList iBillList;
    SimpleDateFormat simpleDateFormat;
    public BillListAdapter(ArrayList<BillListModel> billListModels,
                           IBillList iBillList) {
        this.billListModels = billListModels;
        this.iBillList = iBillList;
    }

    @NonNull
    @Override
    public BillListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bill_list,
                parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillListAdapter.ViewHolder holder, int position) {
        BillListModel billListModel = billListModels.get(position);
//        String date=null;
//        simpleDateFormat = new SimpleDateFormat("dd/m/yyyy");
//        String getDate =String.valueOf(billListModel.getDate());
//        date = String.valueOf(simpleDateFormat.format(getDate));
        holder.textViewBillId.setText(String.valueOf(billListModel.getBillId()));
        holder.textViewDate.setText(billListModel.getDate());
        holder.textViewName.setText(billListModel.getName());
        holder.textViewMobileNo.setText(billListModel.getMobile());
        holder.textViewAmount.setText(billListModel.getTotal());
        holder.textViewPayed.setText(billListModel.getAdvance());
        holder.buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBillList.onBillListView(billListModel);
            }
        });
        holder.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBillList.onBillListDelete(billListModel);
            }
        });
        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iBillList.onBillListEdit(billListModel);
            }
        });
    }
    @Override
    public int getItemCount() {
        return billListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewBillId;
        TextView textViewDate;
        TextView textViewName;
        TextView textViewMobileNo;
        TextView textViewAmount;
        TextView textViewPayed;
        AppCompatButton buttonView;
        AppCompatButton buttonDel;
        AppCompatButton buttonEdit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBillId = itemView.findViewById(R.id.text_row_bill_list_bill_no);
            textViewDate = itemView.findViewById(R.id.text_row_bill_list_date);
            textViewName= itemView.findViewById(R.id.text_row_bill_list_name);
            textViewMobileNo= itemView.findViewById(R.id.text_row_bill_list_mobile_no);
            textViewAmount= itemView.findViewById(R.id.text_row_bill_list_amount);
            textViewPayed= itemView.findViewById(R.id.text_row_bill_list_payed);
            buttonDel = itemView.findViewById(R.id.button_row_bill_list_del);
            buttonView = itemView.findViewById(R.id.button_row_bill_list_view);
            buttonEdit = itemView.findViewById(R.id.button_row_bill_list_edit);
        }
    }
}
