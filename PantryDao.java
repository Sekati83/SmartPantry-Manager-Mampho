package com.example.smartpantrymanager;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PantryDao {
    @Insert
    void insert(PantryItem item);

    @Query("SELECT * FROM pantry_items ORDER BY expiryDate ASC")
    List<PantryItem> getAllItems();

    @Delete
    void delete(PantryItem item);

    @Query("DELETE FROM pantry_items")
    void deleteAll();

    @Query("DELETE FROM pantry_items WHERE name = :name")
    void deleteByName(String name);

    @Query("SELECT * FROM pantry_items WHERE name = :name LIMIT 1")
    PantryItem getByName(String name);

    @Update
    void update(PantryItem item);

    @Query("SELECT * FROM pantry_items")
    List<PantryItem> getAll();

    @Query("UPDATE pantry_items SET quantity = :quantity WHERE name = :name")
    void updateQuantity(String name, int quantity);

    @Query("DELETE FROM pantry_items")
    void clearAll();
}
