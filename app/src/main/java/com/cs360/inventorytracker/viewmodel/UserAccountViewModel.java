package com.cs360.inventorytracker.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.cs360.inventorytracker.model.UserAccount;
import com.cs360.inventorytracker.repo.InventoryRepo;

public class UserAccountViewModel extends AndroidViewModel {
    private final InventoryRepo mInventoryRepo;

    public UserAccountViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepo = InventoryRepo.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<UserAccount> getUser(String email) {
        return mInventoryRepo.getUser(email);
    }

    public boolean addUser(UserAccount user) {
       return mInventoryRepo.addUser(user);
    }

    public void updateUser(UserAccount user) {
        mInventoryRepo.updateUser(user);
    }

    public void deleteUser(UserAccount user) {
        mInventoryRepo.deleteUser(user);
    }
}
