package com.cs360.inventorytracker.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;

import com.cs360.inventorytracker.R;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.repo.InventoryRepository;

import java.util.concurrent.TimeUnit;

public class InventoryItemViewModel extends AndroidViewModel {
    private final InventoryRepository mInventoryRepository;

    public InventoryItemViewModel(@NonNull Application application) {
        super(application);

        mInventoryRepository = InventoryRepository.getInstance(
                application.getApplicationContext()
        );
    }

    public LiveData<InventoryItem> getInventoryItem(Long id) {
        return mInventoryRepository.getInventoryItem(id);
    }

    public void addInventoryItem(InventoryItem item) {
        mInventoryRepository.addInventoryItem(item);
    }

    public void updateInventoryItem(InventoryItem item) {
        mInventoryRepository.updateInventoryItem(item);
    }

    public void deleteInventoryItem(InventoryItem item, View view) {
        mInventoryRepository.deleteInventoryItem(item);
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Navigation.findNavController(view)
                .navigate(R.id.fragment_inventory);
    }

    public void deleteInventoryItemById(Long id){
        mInventoryRepository.deleteInventoryItemById(id);
    }
}
