package com.example.android.foodiego;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class reg_user extends AppCompatActivity {
    ImageView imageView;
    Button reg_button;
    Toolbar toolbar;
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

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        selectedImageUri= data.getData();
        imageView.setImageURI(selectedImageUri);
    }

//    private void uploadData() {
//        FirebaseUser user = auth.getCurrentUser();
//
//        if (user != null) {
//            final String userId = user.getUid();
//
//            final String address = addressEditText.getText().toString();
//            final String name = nameEditText.getText().toString();
//
//            if (selectedImageUri != null) {
//                // Upload image to Firebase Storage
//                final StorageReference imageRef = storageReference.child("profile_images/" + userId);
//                imageRef.putFile(selectedImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                        if (task.isSuccessful()) {
//                            // Get the download URL for the image
//                            imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Uri> task) {
//                                    if (task.isSuccessful()) {
//                                        String imageUrl = task.getResult().toString();
//                                        // Save user data with image URL to Firebase Realtime Database
//                                        User userProfile = new User(userId, name, address, imageUrl);
//                                        databaseReference.child(userId).setValue(userProfile);
//
//                                        addressEditText.setText("");
//                                        nameEditText.setText("");
//
//                                        Toast.makeText(reg_user.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
////                                        Intent intent = new Intent(creat_profile.this, profile.class);
////                                        startActivity(intent);
//                                    }
//                                }
//                            });
//                        } else {
//                            Toast.makeText(reg_user.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//            else {
//                // Save user data without image URL to Firebase Realtime Database
//                User userProfile = new User(userId, name, nickname, age, address, "");
//
//                databaseReference.child(userId).setValue(userProfile);
//                Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


}