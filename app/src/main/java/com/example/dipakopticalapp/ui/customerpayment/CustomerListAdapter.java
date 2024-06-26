package com.example.dipakopticalapp.ui.customerpayment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.webservices.customerpayment.CustomerPaymentListModel;

import java.util.ArrayList;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {
    ArrayList<CustomerPaymentListModel> customerPaymentListModels;
    ICustomerList iCustomerList;

    public CustomerListAdapter(ArrayList<CustomerPaymentListModel> customerPaymentListModels,
                               ICustomerList iCustomerList)
    {
        this.customerPaymentListModels = customerPaymentListModels;
        this.iCustomerList = iCustomerList;
    }

    @NonNull
    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_customer_payment_list,
                parent,false);

        return new CustomerListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.ViewHolder holder, int position) {
        CustomerPaymentListModel customerPaymentListModel= customerPaymentListModels.get(position);
        holder.textViewDate.setText(customerPaymentListModel.getDate());
        holder.textViewName.setText(customerPaymentListModel.getName());
        holder.textViewMobileNo.setText(customerPaymentListModel.getTotal());
        holder.textViewPayed.setText(customerPaymentListModel.getAdvance());
        holder.textViewBalance.setText(customerPaymentListModel.getBalance());
//        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                iCustomerList.onCustomerListEdit(customerPaymentListModel);
//            }
//        });
        holder.buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCustomerList.onCustomerListDelete(customerPaymentListModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return customerPaymentListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate;
        TextView textViewName;
        TextView textViewMobileNo;
        TextView textViewPayed;
        TextView textViewBalance;
        AppCompatButton buttonEdit;
        AppCompatButton buttonDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.text_row_date);
            textViewName= itemView.findViewById(R.id.text_row_name);
            textViewMobileNo= itemView.findViewById(R.id.text_row_mobile_no);
            textViewPayed= itemView.findViewById(R.id.text_row_payed);
            textViewBalance = itemView.findViewById(R.id.text_row_balance);
            buttonDel = itemView.findViewById(R.id.button_row_del);
//            buttonEdit = itemView.findViewById(R.id.button_row_edit);
        }
    }
}
