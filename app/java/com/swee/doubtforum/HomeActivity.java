package com.swee.doubtforum;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button askBtn = findViewById(R.id.askBtn);
        Button viewBtn = findViewById(R.id.viewBtn);

        askBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, AskQuestionActivity.class));
        });

        viewBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, QuestionsListActivity.class));
        });
    }
}