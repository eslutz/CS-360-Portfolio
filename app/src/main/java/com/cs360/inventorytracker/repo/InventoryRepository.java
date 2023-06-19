package com.cs360.inventorytracker.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.cs360.inventorytracker.model.InventoryItem;
import com.cs360.inventorytracker.model.UserAccount;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.List;

public class InventoryRepository {
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService mDatabaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static final String DATABASE_NAME = "inventory.db";
    private static InventoryRepository mInventoryRepository;
    private final UserAccountDao mUserAccountDao;
    private final InventoryItemDao mInventoryItemDao;
    private Boolean mUserSucessfullyAdded = false;
    private Long mIdForItemSucessfullyAdded;

    public static InventoryRepository getInstance(Context context) {
        if (mInventoryRepository == null) {
            mInventoryRepository = new InventoryRepository(context);
        }

        return mInventoryRepository;
    }

    private InventoryRepository(Context context) {
        InventoryDatabase database = Room.databaseBuilder(
                        context,
                        InventoryDatabase.class,
                        DATABASE_NAME)
                .allowMainThreadQueries()
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
        mDatabaseExecutor.execute(() ->
            mUserSucessfullyAdded = mUserAccountDao.addUser(user) != -1
        );

        return mUserSucessfullyAdded;
    }

    public void updateUser(UserAccount user) {
        mDatabaseExecutor.execute(() -> mUserAccountDao.updateUser(user));
    }

    public void deleteUser(UserAccount user) {
        mDatabaseExecutor.execute(() -> mUserAccountDao.deleteUser(user));
    }

    // Inventory table
    public LiveData<List<InventoryItem>> getInventoryList() {
        return mInventoryItemDao.getInventoryList();
    }

    public LiveData<InventoryItem> getInventoryItem(Long itemId) {
        return mInventoryItemDao.getInventoryItem(itemId);
    }

    public void addInventoryItem(InventoryItem item) {
        mDatabaseExecutor.execute(() -> mInventoryItemDao.addInventoryItem(item));
    }

    public void updateInventoryItem(InventoryItem item) {
        mDatabaseExecutor.execute(() -> mInventoryItemDao.updateInventoryItem(item));
    }

    public void deleteInventoryItem(InventoryItem item) {
        mDatabaseExecutor.execute(() -> mInventoryItemDao.deleteInventoryItem(item));
    }

    public void deleteInventoryItemById(Long id) {
        mDatabaseExecutor.execute(() -> mInventoryItemDao.deleteInventoryItemById(id));
    }
}
