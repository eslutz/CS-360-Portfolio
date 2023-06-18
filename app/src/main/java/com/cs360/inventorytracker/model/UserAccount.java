package com.cs360.inventorytracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Locale;

@Entity(indices = {@Index(value = {"email"}, unique = true)})
public class UserAccount {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long mId;
    @ColumnInfo(name = "email")
    private String mEmail;
    @ColumnInfo(name = "password")
    private String mPassword;
    @ColumnInfo(name = "salt")
    private byte[] mSalt;
    @ColumnInfo(name = "updateTime")
    private long mUpdateTime;

    public UserAccount(String email, String password, byte[] salt)
    {
        this.mEmail = email.toLowerCase(Locale.US);
        this.mPassword = password;
        this.mSalt = salt;
        this.mUpdateTime = System.currentTimeMillis();
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
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

    public byte[] getSalt() {
        return mSalt;
    }

    public void setSalt(byte[] salt) {
        mSalt = salt;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }
}
