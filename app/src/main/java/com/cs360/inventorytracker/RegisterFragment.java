package com.cs360.inventorytracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs360.inventorytracker.model.UserAccount;
import com.cs360.inventorytracker.repo.UserAccountDao;
import com.cs360.inventorytracker.viewmodel.UserAccountViewModel;

import java.util.regex.Pattern;

public class RegisterFragment extends Fragment {
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;
    private Button mRegisterButton;
    private boolean validEmailInput = false;
    private boolean validPasswordInput = false;
    private boolean passwordsMatch = false;
    private boolean enableRegisterButton = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_register, container, false);

        mEmail = rootView.findViewById(R.id.email_input);
        mPassword = rootView.findViewById(R.id.password_input);
        mConfirmPassword = rootView.findViewById(R.id.confirm_password_input);
        mRegisterButton = rootView.findViewById(R.id.register_button);

        // Disable the register button on view create
        mRegisterButton.setEnabled(enableRegisterButton);

        // Check that an email address is entered
        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String email = mEmail.getText().toString();
                validEmailInput = !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
                // If not an email address, display error
                if (!validEmailInput) {
                    mEmail.setError("Invalid email");
                } else {
                    // Check if all input is valid
                    enableRegisterButton = validEmailInput &&
                            validPasswordInput &&
                            passwordsMatch;
                    // Enable register button if all input valid
                    mRegisterButton.setEnabled(enableRegisterButton);
                }
            }
        });

        // Check that the password meets complexity and length requirements
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                // Password complexity requires at least one number and one letter
                // with a length of 6 to 20 characters
                Pattern pwPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z]).*$");

                String password = mPassword.getText().toString();
                boolean passwordTooShort = password.length() < 6;
                boolean passwordTooLong = password.length() > 20;
                boolean validPasswordComplexity = pwPattern.matcher(password).matches();

                validPasswordInput = !passwordTooShort &&
                        !passwordTooLong &&
                        validPasswordComplexity;
                if (passwordTooShort) {
                    mPassword.setError("Password must be 6 or more characters");
                } else if (passwordTooLong) {
                    mPassword.setError("Password must be 20 or less characters");
                } else if (!validPasswordComplexity) {
                    mPassword.setError("Password needs at least 1 letter and 1 number");
                } else {
                    // Check if all input is valid
                    enableRegisterButton = validEmailInput &&
                            validPasswordInput &&
                            passwordsMatch;
                    // Enable register button if all input valid
                    mRegisterButton.setEnabled(enableRegisterButton);
                }
            }
        });

        // Check that the same password was entered
        mConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                String password = mPassword.getText().toString();
                String confirmPassword = mConfirmPassword.getText().toString();

                passwordsMatch = confirmPassword.equals(password);
                if (!passwordsMatch) {
                    mConfirmPassword.setError("Passwords must match");
                } else {
                    // Check if all input is valid
                    enableRegisterButton = validEmailInput &&
                            validPasswordInput &&
                            passwordsMatch;
                    // Enable register button if all input valid
                    mRegisterButton.setEnabled(enableRegisterButton);
                }
            }
        });

        mRegisterButton.setOnClickListener(v -> {
            String email = mEmail.getText().toString();
            String password = mPassword.getText().toString();

            if (enableRegisterButton) {
                // Register the new user
                UserAccountViewModel userAccountViewModel = new ViewModelProvider(this)
                        .get(UserAccountViewModel.class);
                UserAccount newUser = new UserAccount(email, password);
                long userId = userAccountViewModel.addUser(newUser);
                if (userId != -1) {
                    Navigation.findNavController(rootView)
                            .navigate(R.id.fragment_login);
                } else {
                    Toast.makeText(
                        rootView.getContext(),
                        "This email is already in use",
                        Toast.LENGTH_LONG)
                        .show();
                }

            }
        });

        return rootView;
    }
}
