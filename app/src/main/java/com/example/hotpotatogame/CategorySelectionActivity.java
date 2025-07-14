package com.example.hotpotatogame; // Package name for your app

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// Main category selection screen
public class CategorySelectionActivity extends AppCompatActivity {

    // Declare buttons
    Button btnParty, btnThinkers, btnPhysical, btnWild;
    Button btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selection); // Link to your XML layout

        // Link Java buttons to XML buttons
        btnParty = findViewById(R.id.btnParty);
        btnThinkers = findViewById(R.id.btnThinkers);
        btnPhysical = findViewById(R.id.btnPhysical);
        btnWild = findViewById(R.id.btnWild);

        // When Party button is clicked → start game with "party" category
        btnParty.setOnClickListener(v -> startGame("party"));

        // When Thinkers button is clicked → start game with "thinkers" category
        btnThinkers.setOnClickListener(v -> startGame("thinkers"));

        // When Physical button is clicked → start game with "physical" category
        btnPhysical.setOnClickListener(v -> startGame("physical"));

        // When Wild button is clicked → start game with "wild" category
        btnWild.setOnClickListener(v -> startGame("wild"));
        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> finishAffinity());  // closes the app completely

    }

    // Method to launch HotPotatoActivity and pass the selected category
    private void startGame(String category) {
        Intent intent = new Intent(CategorySelectionActivity.this, HotPotatoActivity.class); // Navigate to next screen
        intent.putExtra("category", category); // Send category to next screen
        startActivity(intent); // Start HotPotatoActivity
    }
}
