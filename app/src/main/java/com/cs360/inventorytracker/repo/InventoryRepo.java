package com.cs360.inventorytracker.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.model.UserAccount;

import java.util.List;

public class InventoryRepo {
    private static final String DATABASE_NAME = "inventory.db";
    private static InventoryRepo mInventoryRepo;
    private final UserAccountDao mUserAccountDao;
    private final InventoryItemDao mInventoryItemDao;

    public static InventoryRepo getInstance(Context context) {
        if (mInventoryRepo == null) {
            mInventoryRepo = new InventoryRepo(context);
        }

        return mInventoryRepo;
    }

    private InventoryRepo(Context context) {
        InventoryDatabase database = Room.databaseBuilder(
                context,
                InventoryDatabase.class,
                DATABASE_NAME)
                .allowMainThreadQueries()   // todo: remove allow main thread queries
                .fallbackToDestructiveMigration()
                .build();

        mUserAccountDao = database.userAccountDao();
        mInventoryItemDao = database.inventoryItemDao();
    }

    // User table
    public LiveData<UserAccount> getUser(String email) {
        return mUserAccountDao.getUser(email);
    }

    public Boolean addUser(UserAccount user) {
        return mUserAccountDao.addUser(user) != -1;
    }

    public void updateUser(UserAccount user) {
        mUserAccountDao.updateUser(user);
    }

    public void deleteUser(UserAccount user) {
        mUserAccountDao.deleteUser(user);
    }

    // Inventory table
    public LiveData<List<InventoryItem>> getInventoryList() {
        return mInventoryItemDao.getInventoryList();
    }

    public LiveData<InventoryItem> getInventoryItem(Long itemId) {
        return mInventoryItemDao.getInventoryItem(itemId);
    }
//    public List<InventoryItem> getInventoryList() {
//        return mInventoryItemDao.getInventoryList();
//    }
//
//    public InventoryItem getInventoryItem(long itemId) {
//        return mInventoryItemDao.getInventoryItem(itemId);
//    }

    public long addInventoryItem(InventoryItem item) {
        return mInventoryItemDao.addInventoryItem(item);
    }

    public void updateInventoryItem(InventoryItem item) {
        mInventoryItemDao.updateInventoryItem(item);
    }

    public void deleteInventoryItem(InventoryItem item) {
        mInventoryItemDao.deleteInventoryItem(item);
    }
}
