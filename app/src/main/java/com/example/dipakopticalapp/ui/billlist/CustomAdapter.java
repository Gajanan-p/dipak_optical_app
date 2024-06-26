package com.example.dipakopticalapp.ui.billlist;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.webservices.billlist.BillListModel;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

     ArrayList<BillListModel> userList;
     ArrayList<BillListModel> filteredUserList;
     Context context;
    CustomItemClickListener customItemClickListener;
     int position;
    public CustomAdapter(Context context, ArrayList<BillListModel> userArrayList,
                         CustomItemClickListener customItemClickListener
    ) {
        this.context = context;
        this.userList = userArrayList;
        this.filteredUserList = userArrayList;
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_dialog_search_bill, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for click item listener
                customItemClickListener.onItemClick(filteredUserList.get(myViewHolder.getAdapterPosition()),myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        BillListModel model = filteredUserList.get(position);
        viewHolder.textViewId.setText(model.getBillId().toString());
        viewHolder.userName.setText(model.getName());
        viewHolder.userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customItemClickListener.onItemClick(model,position);
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

                    ArrayList<BillListModel> tempFilteredList = new ArrayList<>();

                    for (BillListModel model : userList) {
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
                filteredUserList = (ArrayList<BillListModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;
        private TextView textViewId;
//        ,textViewUnit,textViewRate,textViewCode;
        public MyViewHolder(View view) {
            super(view);
            userName = (TextView)view.findViewById(R.id.row_dialog_bill_name);
//            textViewCode =(TextView) view.findViewById(R.id.row_dialog_item_code);
//            textViewUnit = (TextView)view.findViewById(R.id.row_dialog_item_name);
            textViewId = (TextView)view.findViewById(R.id.row_dialog_bill_id);
//            textViewRate = (TextView)view.findViewById(R.id.row_dialog_item_name);

        }
    }
}

