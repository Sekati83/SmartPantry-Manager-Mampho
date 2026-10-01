package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView txtName = findViewById(R.id.txtDetailName);
        TextView txtIng = findViewById(R.id.txtDetailIngredients);
        TextView txtSteps = findViewById(R.id.txtDetailSteps);

        // Get data from SuggestedActivity
        String name = getIntent().getStringExtra("name");
        String ingredients = getIntent().getStringExtra("ingredients");
        String steps = getIntent().getStringExtra("steps");

        txtName.setText(name);
        txtIng.setText("Needs: " + ingredients);
        txtSteps.setText(steps);
    }
}