package com.example.smartpantrymanager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pantry_items")
public class PantryItem {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int quantity;
    public String category;
    public String expiryDate; // dd/MM/yyyy

    public PantryItem(String name, int quantity, String category, String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
        this.expiryDate = expiryDate;
    }
}
