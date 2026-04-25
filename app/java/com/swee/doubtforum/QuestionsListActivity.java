package com.swee.doubtforum;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseFirestore db;
    List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        questions = new ArrayList<>();

        loadQuestions();
    }

    private void loadQuestions() {
        db.collection("Questions")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Question q = new Question(
                                    document.getId(),
                                    document.getString("title"),
                                    document.getString("description"),
                                    document.getString("userId"),
                                    document.getLong("timestamp")
                            );
                            questions.add(q);
                        }
                        QuestionAdapter adapter = new QuestionAdapter(questions);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(this, "Error loading questions", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}