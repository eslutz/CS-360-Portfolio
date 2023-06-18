package com.cs360.inventorytracker.repo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.cs360.inventorytracker.model.UserAccount;

@Dao
public interface UserAccountDao {
    @Query("SELECT * FROM UserAccount WHERE email = :email")
    UserAccount getUser(String email);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long addUser(UserAccount user);

    @Update
    void updateUser(UserAccount user);

    @Delete
    void deleteUser(UserAccount user);
}
