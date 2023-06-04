package com.cs360.inventorytracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RegisterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_register, container, false);

        final Button registerButton = rootView.findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> Navigation.findNavController(rootView)
                .navigate(R.id.fragment_login));

        return rootView;
    }
}
