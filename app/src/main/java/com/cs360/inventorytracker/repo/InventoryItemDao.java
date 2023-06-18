package com.cs360.inventorytracker.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.cs360.inventorytracker.model.InventoryItem;
import java.util.List;

@Dao
public interface InventoryItemDao {
    @Query("SELECT * FROM InventoryItem ORDER BY id")
    LiveData<List<InventoryItem>> getInventoryList();

    @Query("SELECT * FROM InventoryItem WHERE id = :id")
    LiveData<InventoryItem> getInventoryItem(long id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addInventoryItem(InventoryItem item);

    @Update
    void updateInventoryItem(InventoryItem item);

    @Delete
    void deleteInventoryItem(InventoryItem item);
}
