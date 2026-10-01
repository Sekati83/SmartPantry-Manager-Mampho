package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class SuggestedActivity extends AppCompatActivity {
    TextView txtSuggested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested);
        txtSuggested = findViewById(R.id.txtSuggested);

        new Thread(() -> {
            List<Recipe> recipes = AppDatabase.getInstance(this).recipeDao().getAllRecipes();
            StringBuilder sb = new StringBuilder();
            if (recipes.isEmpty()) {
                sb.append("No recipes found. Uninstall app and run again to seed.");
            } else {
                for (Recipe r : recipes) {
                        sb.append("egg ").append(r.name).append("\n").append(r.ingredients).append("\n\n");
                    }
                }
            runOnUiThread(() -> txtSuggested.setText(sb.toString()));
        }).start();
    }
}



