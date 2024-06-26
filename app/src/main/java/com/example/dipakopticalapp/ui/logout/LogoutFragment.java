package com.example.dipakopticalapp.ui.logout;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dipakopticalapp.MainActivity;
import com.example.dipakopticalapp.R;
import com.example.dipakopticalapp.ui.utils.AppPreference;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;

    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.logout_fragment, container, false);

        final TextView textView = root.findViewById(R.id.text_logout);
        AppPreference.clearLoginDataPreferences(getContext());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent=new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();

    }

}