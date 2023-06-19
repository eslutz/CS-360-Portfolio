package com.cs360.inventorytracker.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.cs360.inventorytracker.model.UserAccount;
import com.cs360.inventorytracker.repo.InventoryRepository;

public class UserAccountViewModel extends AndroidViewModel {
    private final InventoryRepository mInventoryRepository;

    public UserAccountViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepository = InventoryRepository.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<UserAccount> getUser(String email) {
        return mInventoryRepository.getUser(email);
    }

    public boolean addUser(UserAccount user) {
       return mInventoryRepository.addUser(user);
    }

    public void updateUser(UserAccount user) {
        mInventoryRepository.updateUser(user);
    }

    public void deleteUser(UserAccount user) {
        mInventoryRepository.deleteUser(user);
    }
}
