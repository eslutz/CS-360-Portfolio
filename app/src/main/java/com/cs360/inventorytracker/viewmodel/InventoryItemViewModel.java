package com.cs360.inventorytracker.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import com.cs360.inventorytracker.R;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepo;

import java.util.concurrent.TimeUnit;

public class InventoryItemViewModel extends AndroidViewModel {
    private final InventoryRepo mInventoryRepo;

    public InventoryItemViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepo = InventoryRepo.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<InventoryItem> getInventoryItem(Long id) {
        return mInventoryRepo.getInventoryItem(id);
    }

    public long addInventoryItem(InventoryItem item) {
        return mInventoryRepo.addInventoryItem(item);
    }

    public void updateInventoryItem(InventoryItem item) {
        mInventoryRepo.updateInventoryItem(item);
    }

    public void deleteInventoryItem(InventoryItem item, View view) {
        mInventoryRepo.deleteInventoryItem(item);
        try {
            TimeUnit.NANOSECONDS.sleep(1);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Navigation.findNavController(view)
            .navigate(R.id.fragment_inventory);
    }
}
