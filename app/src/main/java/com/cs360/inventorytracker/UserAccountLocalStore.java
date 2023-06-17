package com.cs360.inventorytracker;

import android.content.Context;
import android.content.SharedPreferences;

import com.cs360.inventorytracker.model.UserAccount;

public class UserAccountLocalStore {
    // Name for the shared preference file
    public static final String SP_NAME = "userAccounts";
    SharedPreferences userAccountsLocalDatabase;

    public UserAccountLocalStore(Context context) {
        userAccountsLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserAccount(UserAccount user) {
        SharedPreferences.Editor spEditor = userAccountsLocalDatabase.edit();
        spEditor.putString("email", user.getEmail());
        spEditor.putString("password", user.getPassword());
        spEditor.commit();
    }

    public UserAccount getLoggedInUser() {
        String email = userAccountsLocalDatabase.getString("email", "");
        String password = userAccountsLocalDatabase.getString("password", "");

        UserAccount storedUser = new UserAccount(email, password);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = userAccountsLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getIsUserLoggedIn() {
        return userAccountsLocalDatabase.getBoolean("loggedIn", false);
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userAccountsLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
