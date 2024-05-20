package com.example.android.foodiego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class productDetails extends AppCompatActivity {

    int totQuantity = 1;
    int totPrice;
    String itemPrice;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        // Get references to views
        ImageView imageView = findViewById(R.id.detpro_img);
        TextView nameTextView = findViewById(R.id.detpro_name);
        TextView priceTextView = findViewById(R.id.detpro_price);
        TextView descTextView = findViewById(R.id.detpro_desc);
        TextView quantity = findViewById(R.id.quantity);
        ImageView add_item = findViewById(R.id.add_item);
        ImageView rem_item = findViewById(R.id.minus_item);
        Button add_to_cart = findViewById(R.id.addtocart);

        add_item.setOnClickListener(v -> {
            if(totQuantity < 10) {
                totQuantity++;
                quantity.setText(String.valueOf(totQuantity));
                totPrice = Integer.parseInt(itemPrice) * totQuantity;
            }
        });
        rem_item.setOnClickListener(v -> {
            if(totQuantity > 0) {
                totQuantity--;
                quantity.setText(String.valueOf(totQuantity));
                totPrice = Integer.parseInt(itemPrice) * totQuantity;
            }
        });

        // Get item details from intent
        Intent intent = getIntent();
        int itemImage = intent.getIntExtra("item_image", 0);
        String itemName = intent.getStringExtra("item_name");
        itemPrice = intent.getStringExtra("item_price");
        String itemDescription = intent.getStringExtra("item_description");

        add_to_cart.setOnClickListener(v -> {
            if(totQuantity == 0)
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            else
                addToCart();
        });

        // Set item details to views
        imageView.setImageResource(itemImage);
        nameTextView.setText(itemName);
        priceTextView.setText("Price: " + itemPrice + " $");
        descTextView.setText(itemDescription);
    }
    private void addToCart() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cart_items");
            cartRef.orderByChild("price").equalTo(String.valueOf(itemPrice)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            CartPageItem existingItem = snapshot.getValue(CartPageItem.class);


                            // Increase the quantity and update total price of the existing item
                            int updatedQuantity = existingItem.getQuantity() + totQuantity;
                            int updatedPrice = Integer.parseInt(itemPrice) * updatedQuantity;

                            snapshot.getRef().child("quantity").setValue(updatedQuantity);
                            snapshot.getRef().child("totalPrice").setValue(updatedPrice);

                            Toast.makeText(getApplicationContext(), "Item quantity updated in cart", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    // If item does not exist, add it to the cart
                    String cartItemId = cartRef.push().getKey();
                    int totPrice = Integer.parseInt(itemPrice) * totQuantity;

                    CartPageItem cartPageItem = new CartPageItem(itemPrice, totQuantity, totPrice);
                    cartRef.child(cartItemId).setValue(cartPageItem)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(getApplicationContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show();
                            });
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(getApplicationContext(), "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
    }
}