package com.example.dipakopticalapp.ui.billlist;

import com.example.dipakopticalapp.webservices.bill.ViewBillModel;
import com.example.dipakopticalapp.webservices.billlist.BillListModel;


public interface IBillList {
    void onBillListView(BillListModel billListModel);
    void onBillListDelete(BillListModel billListModel);
    void onBillListEdit(BillListModel billListModel);
}
