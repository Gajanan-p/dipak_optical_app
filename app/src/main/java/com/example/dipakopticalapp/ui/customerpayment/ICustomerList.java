package com.example.dipakopticalapp.ui.customerpayment;


import com.example.dipakopticalapp.webservices.customerpayment.CustomerPaymentListModel;

public interface ICustomerList {
    void onCustomerListEdit(CustomerPaymentListModel customerPaymentListModel);
    void onCustomerListDelete(CustomerPaymentListModel customerPaymentListModel);
}
