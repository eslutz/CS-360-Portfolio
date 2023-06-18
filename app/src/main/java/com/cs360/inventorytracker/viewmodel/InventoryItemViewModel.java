package com.cs360.inventorytracker.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepo;

public class InventoryItemViewModel extends AndroidViewModel {
    private final InventoryRepo mInventoryRepo;

    public InventoryItemViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepo = InventoryRepo.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<InventoryItem> getInventoryItem(long id) {
        return mInventoryRepo.getInventoryItem(id);
    }
//    public InventoryItem getInventoryItem(long id) {
//        return mInventoryRepo.getInventoryItem(id);
//    }

    public long addInventoryItem(InventoryItem item) {
        return mInventoryRepo.addInventoryItem(item);
    }

    public void updateInventoryItem(InventoryItem item) {
        mInventoryRepo.updateInventoryItem(item);
    }

    public void deleteInventoryItem(InventoryItem item) {
        mInventoryRepo.deleteInventoryItem(item);
    }
}
