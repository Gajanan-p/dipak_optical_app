package com.example.dipakopticalapp.ui.login;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dipakopticalapp.MainActivity;
import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.ui.utils.AppPreference;
import com.example.dipakopticalapp.webservices.login.LoginModel;
import com.example.dipakopticalapp.webservices.login.LoginReq;
import com.example.dipakopticalapp.webservices.Webservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private LoginViewModel mViewModel;
    LoginModel loginModel;
    NavController navController;
    private ProgressDialog progressDialog;
    AppCompatEditText appCompatEditTextLoginName;
    AppCompatEditText appCompatEditTextPassword;
    AppCompatButton appCompatButtonLogin;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        appCompatEditTextLoginName=view.findViewById(R.id.edit_login_user_name);
        appCompatEditTextPassword=view.findViewById(R.id.edit_password);
        appCompatButtonLogin=view.findViewById(R.id.button_login);
        appCompatButtonLogin.setOnClickListener(this);

       // loginModel= AppPreference.getLoginDataPreferences(getContext());

//        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
//        if(loginModel!=null){
//
//        }
//        else {
//            Toast.makeText(getContext(),"LoginModel Null Value",Toast.LENGTH_LONG).show();
//        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
    }
 void checkCredentialWithServer() {
        progressDialog = createProgressDialog(getContext());
     if (!TextUtils.isEmpty(appCompatEditTextLoginName.getText())
             && !TextUtils.isEmpty(appCompatEditTextPassword.getText())) {

         LoginReq loginReq = new LoginReq(appCompatEditTextLoginName.getText().toString(),
                 appCompatEditTextPassword.getText().toString());

         Call<LoginModel> listUser = Webservice
                 .iLoginUser()
                 .listUser(loginReq);
         listUser.enqueue(new Callback<LoginModel>() {
             @Override
             public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                 if (response.isSuccessful()) {
                     loginModel = response.body();
                     if (loginModel.getMobileNo()!= null) {
                         navController.navigate(R.id.action_nav_loginFragment_to_nav_home);
                     AppPreference.setLoginDataPreferences(getContext(), loginModel);
//                     NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
//                     navController.navigate(R.id.nav_home);
//                     Intent intent = new Intent(getActivity(), MainActivity.class);
//                     startActivity(intent);
//                     getActivity().finish();
                     Toast.makeText(getContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                         progressDialog.dismiss();
                     }
                     else{
                         Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                     }
                     progressDialog.dismiss();
                 }
             }

             @Override
             public void onFailure(Call<LoginModel> call, Throwable t) {
                 Toast.makeText(getContext(),"Check Internet Connection",Toast.LENGTH_LONG).show();
             }
         });

     }
   }

    public ProgressDialog createProgressDialog(Context mContext) {
        ProgressDialog dialog = new ProgressDialog(mContext);
        try {
            dialog.show();
        } catch (WindowManager.BadTokenException e) {

        }
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.blue(100)));
        dialog.setContentView(R.layout.dialog_progress_layout);
        return dialog;
    }
    @Override
    public void onClick(View v) {
        checkCredentialWithServer();
    }

 }