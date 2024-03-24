package com.example.android.foodiego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class reg_user extends AppCompatActivity {
    ImageView imageView;
    Button reg_button;
    FloatingActionButton floatingActionButton;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    EditText addressEditText, mobileEditText,emailEditText,passEditText, nameEditText;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user);

        imageView = findViewById(R.id.img_id);
        floatingActionButton = findViewById(R.id.floatingActionButton_id);
        ImageButton eyeButton = findViewById(R.id.eye_button);
        final EditText passwordEditText = findViewById(R.id.reg_password_editText);

        reg_button = findViewById(R.id.reg_button_id);
        addressEditText = findViewById(R.id.reg_address_editText);
        nameEditText = findViewById(R.id.reg_name_editText);
        mobileEditText = findViewById(R.id.reg_mobile_editText);
        emailEditText = findViewById(R.id.reg_email_editText);
        passEditText = findViewById(R.id.reg_password_editText);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        floatingActionButton.setOnClickListener(v -> ImagePicker.with(reg_user.this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start()
        );

        eyeButton.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide the password
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeButton.setImageResource(R.drawable.ic_eye_close);
                } else {
                    // Show the password
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                    eyeButton.setImageResource(R.drawable.ic_eye_open);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        reg_button.setOnClickListener(v -> reg_user_details());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectedImageUri= data.getData();
        imageView.setImageURI(selectedImageUri);
    }

    private void reg_user_details() {
        final String email = emailEditText.getText().toString().trim();
        final String password = passEditText.getText().toString().trim();
        final String name = nameEditText.getText().toString().trim();
        final String mobile = mobileEditText.getText().toString().trim();
        final String address = addressEditText.getText().toString().trim();

        if (selectedImageUri != null && !email.isEmpty() && !password.isEmpty() && !name.isEmpty() && !mobile.isEmpty() && !address.isEmpty()) {
            // Upload image to Firebase Storage
            StorageReference imageRef = storageReference.child("profile_images/" + System.currentTimeMillis() + ".jpg");
            imageRef.putFile(selectedImageUri)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                reg_class user = new reg_class(name, email, mobile, address, password);

                                databaseReference.push().setValue(user)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(reg_user.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(this, log_user.class));
                                                finish();
                                            } else {
                                                Toast.makeText(reg_user.this, "Failed to register. Please try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            });
                        } else {
                            Toast.makeText(reg_user.this, "Failed to upload image. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(reg_user.this, "Please fill in all the details and select an image.", Toast.LENGTH_SHORT).show();
        }
    }
}