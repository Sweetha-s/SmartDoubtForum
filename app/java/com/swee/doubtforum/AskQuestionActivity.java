package com.swee.doubtforum;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AskQuestionActivity extends AppCompatActivity {

    EditText title, description;
    Button postBtn;
    ProgressBar progressBar;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        postBtn = findViewById(R.id.postBtn);
        progressBar = findViewById(R.id.progressBar);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        postBtn.setOnClickListener(v -> {
            String t = title.getText().toString().trim();
            String d = description.getText().toString().trim();

            if (TextUtils.isEmpty(t) || TextUtils.isEmpty(d)) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            postQuestion(t, d);
        });
    }

    private void postQuestion(String t, String d) {
        progressBar.setVisibility(View.VISIBLE);
        postBtn.setEnabled(false);

        Map<String, Object> question = new HashMap<>();
        question.put("title", t);
        question.put("description", d);
        question.put("userId", auth.getUid());
        question.put("userEmail", auth.getCurrentUser().getEmail());
        question.put("timestamp", FieldValue.serverTimestamp());
        question.put("likes", new ArrayList<String>());

        db.collection("doubts")
                .add(question)
                .addOnSuccessListener(doc -> {
                    Toast.makeText(this, "Doubt Posted!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    progressBar.setVisibility(View.GONE);
                    postBtn.setEnabled(true);
                    Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
