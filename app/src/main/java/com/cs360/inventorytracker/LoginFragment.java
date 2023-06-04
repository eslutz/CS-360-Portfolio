package com.cs360.inventorytracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class LoginFragment extends Fragment {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_login, container, false);

        final TextView registerLink = rootView.findViewById(R.id.register_link);
        registerLink.setOnClickListener(v -> Navigation.findNavController(rootView)
                .navigate(R.id.fragment_register));
        registerLink.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    registerLink.setShadowLayer(50, 0, 0, R.color.grey);
                    break;
                case MotionEvent.ACTION_UP:
                    registerLink.setShadowLayer(0, 0, 0, R.color.grey);
                    v.performClick();
                    break;
                default:
                    return false;
            }
            return true;
        });

        final Button signInButton = rootView.findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(v -> Navigation.findNavController(rootView)
                .navigate(R.id.fragment_inventory));


        return rootView;
    }
}
