package com.cs360.inventorytracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_login, container, false);

        View loginFragmentContainer = rootView.findViewById(R.id.fragment_login);

        final TextView registerLink = rootView.findViewById(R.id.register_link);
        registerLink.setOnClickListener(v -> Navigation.findNavController(loginFragmentContainer)
                .navigate(R.id.fragment_register));

        final Button signInButton = rootView.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> Navigation.findNavController(loginFragmentContainer)
                .navigate(R.id.fragment_inventory));

        return rootView;
    }
}
