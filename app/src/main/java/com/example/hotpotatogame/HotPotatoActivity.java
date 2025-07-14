package com.example.hotpotatogame;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class HotPotatoActivity extends AppCompatActivity {

    // ✅ Declare views
    private TextView textTask, textTimer;
    private Button btnStart, btnPass;
    private CountDownTimer countDownTimer;
    private int timeLeft = 10000; // 10 seconds countdown
    private Random random = new Random();

    private List<String> tasks; // Will store the task list based on selected category

    // ✅ Map of categories and their associated tasks
    private final Map<String, List<String>> categoryTasks = new HashMap<String, List<String>>() {{
        put("party", Arrays.asList(
                "Name a drink brand",
                "Do your best dance move",
                "Sing the first line of a song",
                "Make a toast"
        ));
        put("thinkers", Arrays.asList(
                "Name a country",
                "Name a football team",
                "Say a word that rhymes with 'fun'",
                "Count backwards from 15"
        ));
        put("physical", Arrays.asList(
                "Do 5 squats",
                "Do a spin then clap",
                "Touch your toes 3 times",
                "Act like a monkey"
        ));
        put("wild", Arrays.asList(
                "Roast the person on your left",
                "Speak in an accent",
                "Pretend to be a celebrity",
                "Tell a secret or drink"
        ));
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_potato);

        // ✅ Link views to XML IDs
        textTask = findViewById(R.id.textTask);
        textTimer = findViewById(R.id.textTimer);
        btnStart = findViewById(R.id.btnStart);
        btnPass = findViewById(R.id.btnPass);

        // ✅ Get selected category from previous screen
        String category = getIntent().getStringExtra("category");

        // ✅ Fallback-safe way to get tasks for selected category
        if (categoryTasks.containsKey(category)) {
            tasks = categoryTasks.get(category);
        } else {
            tasks = Arrays.asList("Random Task");
        }

        // ✅ Start button triggers new round
        btnStart.setOnClickListener(v -> startGame());

        // ✅ Pass button allows passing to next player
        btnPass.setOnClickListener(v -> passTurn());
    }

    // ✅ Starts a new round
    private void startGame() {
        showRandomTask();
        btnStart.setVisibility(View.GONE);
        btnPass.setVisibility(View.VISIBLE);
        startCountdown();
    }

    // ✅ Handles when player passes
    private void passTurn() {
        countDownTimer.cancel();
        startGame();
    }

    // ✅ Countdown logic (ticks every second)
    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                textTimer.setText("0");
                Toast.makeText(HotPotatoActivity.this, "YOU DRINK!", Toast.LENGTH_LONG).show();
                textTask.setText("Drink and press Start for new round.");
                btnStart.setVisibility(View.VISIBLE);
                btnPass.setVisibility(View.GONE);
            }
        }.start();
    }

    // ✅ Shows a random task
    private void showRandomTask() {
        String randomTask = tasks.get(random.nextInt(tasks.size()));
        textTask.setText(randomTask);
    }
}
