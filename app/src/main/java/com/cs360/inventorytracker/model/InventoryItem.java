package com.cs360.inventorytracker.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class InventoryItem {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long mId;
    @ColumnInfo(name = "name")
    private String mName;
    @ColumnInfo(name = "quantity")
    private int mQuantity;
    @ColumnInfo(name = "updateTime")
    private long mUpdateTime;

    public InventoryItem(@NotNull String name, int quantity, Long id) {
        if (id != null) {
            this.mId = id;
        }
        this.mName = name;
        this.mQuantity = quantity;
        this.mUpdateTime = System.currentTimeMillis();
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public long getUpdateTime() {
        return mUpdateTime;
    }

    public void setUpdateTime(long updateTime) {
        mUpdateTime = updateTime;
    }
}
