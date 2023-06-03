package com.cs360.inventorytracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RegisterFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_register, container, false);

        View registerFragmentContainer = rootView.findViewById(R.id.fragment_register);

        final Button registerButton = rootView.findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> Navigation.findNavController(registerFragmentContainer)
                .navigate(R.id.fragment_inventory));

        return rootView;
    }
}
