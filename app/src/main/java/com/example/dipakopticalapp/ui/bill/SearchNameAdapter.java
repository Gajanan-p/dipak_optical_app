package com.example.dipakopticalapp.ui.bill;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.webservices.customer.SearchCustomerModel;

import java.util.ArrayList;


public class SearchNameAdapter extends RecyclerView.Adapter<SearchNameAdapter.MyViewHolder> implements Filterable {

    private ArrayList<SearchCustomerModel> userList;
    private ArrayList<SearchCustomerModel> filteredUserList;
    private Context context;
    private CustomListener customItemClickListener;
    int position;

    public SearchNameAdapter(Context context,ArrayList<SearchCustomerModel> userArrayList,
                             CustomListener customItemClickListener
    )
    {
        this.context = context;
        this.userList = userArrayList;
        this.filteredUserList = userArrayList;
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_dialog_search_customer, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for click item listener
                customItemClickListener.onNameClicked(filteredUserList.get(myViewHolder.getAdapterPosition()),myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder,int position) {
        SearchCustomerModel model = filteredUserList.get(position);
        viewHolder.userId.setText(model.getCustomerId().toString());
        viewHolder.userName.setText(model.getName());
        viewHolder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClickListener.onNameClicked(model,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    filteredUserList = userList;

                } else {

                    ArrayList<SearchCustomerModel> tempFilteredList = new ArrayList<>();

                    for (SearchCustomerModel model : userList) {
                        if (model.getName().length() > 0) {
                            // search for user title
                            if(model.getName().length()<searchString.length()){

                            }
                            else {if(model.getName().substring(0, searchString.length()).toLowerCase().equals(searchString)) {

                                tempFilteredList.add(model);
                            }}
                        }
                    }
                    filteredUserList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredUserList = (ArrayList<SearchCustomerModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userId;
        private TextView userName;
        public MyViewHolder(View view) {
            super(view);
            userId =  (TextView)view.findViewById(R.id.row_dialog_customer_id);
            userName = (TextView)view.findViewById(R.id.row_dialog_customer_name);
        }
    }
}

