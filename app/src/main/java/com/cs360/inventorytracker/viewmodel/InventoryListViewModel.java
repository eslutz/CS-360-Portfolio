package com.cs360.inventorytracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepo;

import java.util.List;

public class InventoryListViewModel extends AndroidViewModel {
    private final InventoryRepo mInventoryRepo;

    public InventoryListViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepo = InventoryRepo.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<List<InventoryItem>> getInventoryList() {
        return mInventoryRepo.getInventoryList();
    }
//    public List<InventoryItem> getInventoryList() {
//        return mInventoryRepo.getInventoryList();
//    }
}
