package com.example.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText etName, etQuantity, etCategory, etExpiry;
    Button btnAdd, btnDelete, btnEdit;
    TextView txtList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etQuantity = findViewById(R.id.etQuantity);
        etCategory = findViewById(R.id.etCategory);
        etExpiry = findViewById(R.id.etExpiry);
        btnAdd = findViewById(R.id.btnAdd);
        txtList = findViewById(R.id.txtList);
        btnDelete = findViewById(R.id.btnDelete);

        try {
            int sugId = getResources().getIdentifier("btnViewRecipes", "id", getPackageName());
            if (sugId == 0) sugId = getResources().getIdentifier("btnSuggestedRecipes", "id", getPackageName());
            if (sugId == 0) sugId = getResources().getIdentifier("btnViewSuggestedRecipes", "id", getPackageName());
            if (sugId == 0) sugId = getResources().getIdentifier("btnRecipes", "id", getPackageName());
            if (sugId != 0) {
                Button btnSug = findViewById(sugId);
                btnSug.setOnClickListener(v -> startActivity(new android.content.Intent(MainActivity.this, SuggestedActivity.class)));
                }
        } catch (Exception e) {}

        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String qtyStr = etQuantity.getText().toString().trim();
            if (name.isEmpty()) return;
            int qty = 1;
            try {
                qty = Integer.parseInt(qtyStr);
            } catch (Exception e) {
            }
            PantryItem item = new PantryItem(name, qty, etCategory.getText().toString(), etExpiry.getText().toString());
            new Thread(() -> {
                AppDatabase.getInstance(this).pantryDao().insert(item);
                runOnUiThread(() -> {
                    showItems();
                    etName.setText("");
                });
            }).start();
        });

        btnDelete.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, "Type name in TOP box to delete", Toast.LENGTH_LONG).show();
                return;
            }
            new Thread(() -> {
                AppDatabase.getInstance(this).pantryDao().deleteByName(name);
                runOnUiThread(() -> {
                    showItems();
                    etName.setText("");
                });
            }).start();
        });

        showItems();
        seedRecipes();
    }

    private void showItems() {
        new Thread(() -> {
            List<PantryItem> items = AppDatabase.getInstance(this).pantryDao().getAllItems();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            StringBuilder sb = new StringBuilder();
            for (PantryItem i : items) {
                sb.append(i.name).append(" - Qty: ").append(i.quantity);
                try {
                    if (i.expiryDate != null && !i.expiryDate.isEmpty()) {
                        Date exp = sdf.parse(i.expiryDate);
                        long days = TimeUnit.DAYS.convert(exp.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
                        if (days <= 3) sb.append(" ! EXPIRING SOON!");
                        if (i.quantity <= 2) sb.append(" ! LOW STOCK!");
                    }
                } catch (Exception e) {
                }
                sb.append("\n");
            }
            runOnUiThread(() -> txtList.setText(sb.toString()));
        }).start();
    }

    public void openSuggested(android.view.View v) {
        startActivity(new android.content.Intent(this,SuggestedActivity.class));
    }

    private void seedRecipes() {
        new Thread(() -> {
            RecipeDao dao = AppDatabase.getInstance(this).recipeDao();
            if (dao.getCount() == 0) {
                dao.insert(new Recipe("Tomato Pasta", "Pasta, Tomato, Cheese", "Boil pasta, add tomato sauce"));
                dao.insert(new Recipe("Cheese Omelette", "Eggs, Cheese, Milk", "Beat eggs, fry with cheese"));
                dao.insert(new Recipe("Fruit Salad", "Apple, Banana, Yogurt", "Cut fruits and mix"));
                dao.insert(new Recipe("Chicken Fried Rice", "Rice, Chicken, Eggs, Soy Sauce", "Fry chicken, add rice and soy sauce"));
                dao.insert(new Recipe("Beef Stew", "Beef, Potato, Onion", "Brown beef, add onion and potato, simmer"));
                dao.insert(new Recipe("Grilled Cheese", "Bread, Cheese, Butter", "Butter bread, add cheese, grill"));
                dao.insert(new Recipe("Vegetable Soup", "Carrot, Potato, Onion", "Chop veg, boil 30 min"));
                dao.insert(new Recipe("Pancakes", "Flour, Eggs, Milk", "Mix and fry"));
                dao.insert(new Recipe("Spaghetti Bolognese", "Spaghetti, Mince, Tomato", "Boil spaghetti, fry mince with tomato"));
                dao.insert(new Recipe("Chicken Wrap", "Tortilla, Chicken, Lettuce", "Gill chicken, wrap it"));
                dao.insert(new Recipe("Tuna Salad", "Tuna, Lettuce, Tomato", "Mix all together"));
                dao.insert(new Recipe("Egg Noodles", "Noodles, Eggs, Veg", "Boil noodles, stir fry with eggs"));
                dao.insert(new Recipe("Potato Mash", "Potato, Butter, Milk", "Boil and mash"));
                dao.insert(new Recipe("Chicken Curry", "Chicken, Curry, Onoin, Rice", "Fry onion, add curry and chicken, serve with rice"));
                dao.insert(new Recipe("Beans on Toast", "Beans, Bread", "Toast bread, heat beans"));
                dao.insert(new Recipe("Avocado Toast", "Bread, Avocado", "Toast, mash avocado on top"));
                dao.insert(new Recipe("Rice and Peas", "Rice, Peas, Onion", "Cook together"));
                dao.insert(new Recipe("Banana Smoothie", "Banana, Milk, Honey", "Blend all"));
            }
        }).start();
    }
}