package com.cs360.inventorytracker.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.model.UserAccount;

@Database(entities = {UserAccount.class, InventoryItem.class}, version = 1)
public abstract class InventoryDatabase extends RoomDatabase {
    public abstract UserAccountDao userAccountDao();
    public abstract InventoryItemDao inventoryItemDao();
}
