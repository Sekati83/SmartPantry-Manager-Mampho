// AppDatabase.java - Room Database configuration for Smart Pantry Manager
// Author: Mampho H Modikeng - MAM700
// This class handles SQLite persistence with migration from version 1 to 2 for category colunm
package com.example.smartpantrymanager;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {PantryItem.class,Recipe.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract PantryDao pantryDao();
    public abstract RecipeDao recipeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "pantry_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
