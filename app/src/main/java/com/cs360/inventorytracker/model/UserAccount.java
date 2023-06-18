package com.cs360.inventorytracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"email"}, unique = true)})
public class UserAccount {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;
    @ColumnInfo(name = "email")
    private String mEmail;
    @ColumnInfo(name = "password")
    private String mPassword;
    @ColumnInfo(name = "updateTime")
    private long mUpdateTime;

    public UserAccount(String email, String password)
    {
        this.mEmail = email;
        // todo: hash password for storage
        this.mPassword = password;
        this.mUpdateTime = System.currentTimeMillis();
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }
}
