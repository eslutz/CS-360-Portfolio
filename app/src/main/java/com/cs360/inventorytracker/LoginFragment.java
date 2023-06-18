package com.cs360.inventorytracker;

import static com.cs360.inventorytracker.HashPassword.generateHash;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.cs360.inventorytracker.viewmodel.UserAccountViewModel;
import java.util.Locale;

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
            String email = mEmail.getText().toString().toLowerCase(Locale.US);
            String password = mPassword.getText().toString();

            // Check that an email and password have been entered
            if (!email.isEmpty() && !password.isEmpty()) {
                UserAccountViewModel userAccountViewModel = new ViewModelProvider(this)
                        .get(UserAccountViewModel.class);
                // Attempt to login the user
                userAccountViewModel.getUser(email).observe(getViewLifecycleOwner(), user -> {
                    String loginPassword = generateHash(password, user.getSalt());
                    UserAccount newUser = new UserAccount(email, loginPassword, user.getSalt());
                    // If the user exists and the password matches then login
                    if (user.getPassword().equals(loginPassword)) {
                        // Keep the user logged in
                        userAccountLocalStore.storeUserAccount(newUser);
                        userAccountLocalStore.setUserLoggedIn(true);
                        // Take user to inventory page
                        Navigation.findNavController(rootView)
                                .navigate(R.id.fragment_inventory);
                    } else {
                        Toast.makeText(
                                        rootView.getContext(),
                                        "The email or password is incorrect",
                                        Toast.LENGTH_LONG)
                                .show();
                    }
                });
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
