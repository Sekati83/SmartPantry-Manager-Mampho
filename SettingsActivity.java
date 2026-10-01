package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button btnClear = findViewById(R.id.btnClearPantry);
        btnClear.setOnClickListener(v -> {
            new Thread(() -> {
                AppDatabase.getInstance(this).clearAllTables();
                runOnUiThread(() -> {
                    Toast.makeText(this, "Pantry Cleared!", Toast.LENGTH_SHORT).show();
                    finish(); // go back to Main
            });
            }).start();
        });
    }
}