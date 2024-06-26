package com.example.dipakopticalapp.ui.billlist;


import com.example.dipakopticalapp.webservices.billlist.BillListModel;

public interface CustomItemClickListener {
   void onItemClick(BillListModel model, int position);
}
