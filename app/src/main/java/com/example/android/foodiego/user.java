package com.example.android.foodiego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user extends AppCompatActivity {

    Button log_user,log_owner,reg_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        log_user = findViewById(R.id.btn_log_user);
        reg_user = findViewById(R.id.btn_reg_user);

        log_user.setOnClickListener(v -> {
            Intent i = new Intent(user.this,log_user.class);
            startActivity(i);
        });
        reg_user.setOnClickListener(v -> {
            Intent i = new Intent(user.this,reg_user.class);
            startActivity(i);
        });
    }
}