package com.swee.doubtforum;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, name;
    Button registerBtn;
    ProgressBar progressBar;
    FirebaseAuth auth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        // Assuming name field was added to XML or using email as default
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = new ProgressBar(this); // Or find it if in XML

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        registerBtn.setOnClickListener(v -> {
            String e = email.getText().toString().trim();
            String p = password.getText().toString().trim();

            if (TextUtils.isEmpty(e) || TextUtils.isEmpty(p)) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            auth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = auth.getUid();
                    Map<String, Object> user = new HashMap<>();
                    user.put("email", e);
                    user.put("uid", uid);

                    db.collection("users").doc(uid).set(user).addOnSuccessListener(aVoid -> {
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        finish();
                    });
                } else {
                    Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
