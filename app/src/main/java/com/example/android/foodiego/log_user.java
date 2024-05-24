package com.example.android.foodiego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class log_user extends AppCompatActivity {
    EditText LoginEmailEditText, LoginPasswordEditText;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_user);

        LoginEmailEditText = findViewById(R.id.login_email_editText);
        LoginPasswordEditText = findViewById(R.id.login_password_editText);
        loginButton = findViewById(R.id.login_button_id);

        FirebaseUser currentUser = FirebaseAuthInstance.getInstance().getCurrentUser();

        if (currentUser != null) {
            startActivity(new Intent(this, home.class));
            finish();
        } else
            loginButton.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {

        String email = LoginEmailEditText.getText().toString();
        String password = LoginPasswordEditText.getText().toString();

        // Check if email or password is empty
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty.", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuthInstance.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuthInstance.getInstance().getCurrentUser();
                    if (user != null) {
                        Toast.makeText(this, "Authentication successful.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, home.class));
                        finish();
                    } else {
                        Toast.makeText(this, "User doeasn't exist. Register First", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(this, reg_user.class));
                    }
                }
                else
                    Toast.makeText(this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
            });
    }

    // Singleton implementation
    private static class FirebaseAuthInstance {
        private static FirebaseAuth instance;

        private FirebaseAuthInstance() {
            // Private constructor to prevent external instantiation
        }

        public static synchronized FirebaseAuth getInstance() {
            if (instance == null) {
                instance = FirebaseAuth.getInstance();
            }
            return instance;
        }
    }
}
