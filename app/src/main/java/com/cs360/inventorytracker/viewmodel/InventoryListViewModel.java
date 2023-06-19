package com.cs360.inventorytracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepository;

import java.util.List;

public class InventoryListViewModel extends AndroidViewModel {
    private final InventoryRepository mInventoryRepository;

    public InventoryListViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepository = InventoryRepository.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<List<InventoryItem>> getInventoryList() {
        return mInventoryRepository.getInventoryList();
    }
}
