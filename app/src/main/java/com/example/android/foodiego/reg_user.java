package com.example.android.foodiego;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseUser;
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
                    passEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeButton.setImageResource(R.drawable.ic_eye_close);
                } else {
                    // Show the password
                    passEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                    eyeButton.setImageResource(R.drawable.ic_eye_open);
                }
                isPasswordVisible = !isPasswordVisible;
            }
        });

        auth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null)
        {
            startActivity(new Intent(this, home.class ));
            finish();
        }
        else
            reg_button.setOnClickListener(v -> reg_user_details());

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        selectedImageUri= data.getData();
        imageView.setImageURI(selectedImageUri);
    }
private void reg_user_details() {
    final String name = nameEditText.getText().toString();
    final String email = emailEditText.getText().toString();
    final String password = passEditText.getText().toString();
    final String address = addressEditText.getText().toString();
    final String mobile = mobileEditText.getText().toString();

    if (name.isEmpty() || email.isEmpty() || password.isEmpty() || address.isEmpty() || mobile.isEmpty()) {
        Toast.makeText(reg_user.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        return;
    }
    auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(reg_user.this, task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    if (user != null) {
                        // Upload image to Firebase Storage if selectedImageUri is not null
                        if (selectedImageUri != null) {
                            StorageReference imageRef = storageReference.child("images/" + user.getUid() + "/profile.jpg");
                            imageRef.putFile(selectedImageUri)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            // Get the download URL for the image
                                            imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                                                // Save user details to Realtime Database
                                                String imageUrl = uri.toString();
                                                reg_class userObject = new reg_class(name, email, address, mobile,password,imageUrl);
                                                databaseReference.child(user.getUid()).child("registered_profile").setValue(userObject)
                                                        .addOnCompleteListener(task2 -> {
                                                            if (task2.isSuccessful()) {
                                                                Toast.makeText(reg_user.this, "Registration successful", Toast.LENGTH_LONG).show();
                                                                startActivity(new Intent(reg_user.this, log_user.class));
                                                                finish();
                                                            } else
                                                                Toast.makeText(reg_user.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                                                        });
                                            });
                                        } else {
                                            reg_class userObject = new reg_class(name, email, address, mobile,password,"");
                                            databaseReference.child(user.getUid()).child("registered_profile").setValue(userObject)
                                                    .addOnCompleteListener(task2 -> {
                                                        if (task2.isSuccessful()) {
                                                            Toast.makeText(reg_user.this, "Registration successful (without profile image)", Toast.LENGTH_LONG).show();
                                                            startActivity(new Intent(reg_user.this, log_user.class));
                                                            finish();
                                                        } else
                                                            Toast.makeText(reg_user.this, "Failed to register user 2", Toast.LENGTH_SHORT).show();
                                                    });
                                        }
                                    });
                            }
                        }
                    else
                        Toast.makeText(reg_user.this, "User is null", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(reg_user.this, "user exists", Toast.LENGTH_SHORT).show();
            });
    }
}
