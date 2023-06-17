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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cs360.inventorytracker.model.UserAccount;

public class LoginFragment extends Fragment {
    private UserAccountLocalStore userAccountLocalStore;
    private EditText mEmail;
    private EditText mPassword;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_login, container, false);
        userAccountLocalStore = new UserAccountLocalStore(rootView.getContext());
        mEmail = rootView.findViewById(R.id.email_input);
        mPassword = rootView.findViewById(R.id.password_input);

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
        signInButton.setOnClickListener(v -> {
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            // Check that an email and password have been entered
            if (!email.isEmpty() && !password.isEmpty()) {
                // Attempt to login the user
                UserAccount user = new UserAccount(email, password);
                // todo: check user info against database and display any errors

                // Keep the user logged in
                userAccountLocalStore.storeUserAccount(user);
                userAccountLocalStore.setUserLoggedIn(true);
                Navigation.findNavController(rootView)
                        .navigate(R.id.fragment_inventory);
            } else {
                Toast.makeText(
                        rootView.getContext(),
                        "Email and password cannot be empty",
                        Toast.LENGTH_LONG)
                        .show();
            }

        });


        return rootView;
    }
}
