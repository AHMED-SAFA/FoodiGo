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

//private void addToCart() {
//    // Get the current user
//    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    if (user != null) {
//        // Get user ID
//        String userId = user.getUid();
//
//        // Get reference to the cart_items node for the user
//        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cart_items");
//
//        // Generate a unique key for the new cart item
//        String cartItemId = cartRef.push().getKey();
//
//        // Calculate total price
//        int totPrice = Integer.parseInt(itemPrice) * totQuantity;
//
//        // Create a new CartPageItem
//        CartPageItem cartPageItem = new CartPageItem(itemPrice, totQuantity, totPrice);
//
//        // Add the new item to the cart
//        if (cartItemId != null) {
//            cartRef.child(cartItemId).setValue(cartPageItem)
//                .addOnSuccessListener(aVoid -> {
//                    Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(getApplicationContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show();
//                });
//        }
//        else {
//            Toast.makeText(getApplicationContext(), "Failed to generate cart item ID", Toast.LENGTH_SHORT).show();
//        }
//    }
//    else {
//        Toast.makeText(getApplicationContext(), "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
//    }
//}


//    private void addToCart() {
//        // Get the current user
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Get user ID
//            String userId = user.getUid();
//
//            // Get reference to the cart_items node for the user
//            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cart_items");
//
//            // Check if item with the same price exists in the cart
//            cartRef.orderByChild("price").equalTo(itemPrice).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.exists()) {
//                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                            // Get existing cart item
//                            CartPageItem existingItem = snapshot.getValue(CartPageItem.class);
//
//                            // Update quantity and total price
//                            int updatedQuantity = existingItem.getQuantity() + totQuantity;
//                            int updatedTotalPrice = Integer.parseInt(itemPrice) * updatedQuantity;
//
//                            // Update existing item in the cart
//                            snapshot.getRef().child("quantity").setValue(updatedQuantity);
//                            snapshot.getRef().child("totalPrice").setValue(updatedTotalPrice);
//
//                            Toast.makeText(getApplicationContext(), "Item quantity updated in cart", Toast.LENGTH_SHORT).show();
//                            return; // Exit loop after updating the item
//                        }
//                    }
//                    else {
//                        // If item with the same price does not exist, add it to the cart
//                        String cartItemId = cartRef.push().getKey();
//                        int totPrice = Integer.parseInt(itemPrice) * totQuantity;
//
//                        CartPageItem cartPageItem = new CartPageItem(itemPrice, totQuantity, totPrice);
//
//                        if (cartItemId != null) {
//                            cartRef.child(cartItemId).setValue(cartPageItem)
//                                .addOnSuccessListener(aVoid -> {
//                                    Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
//                                })
//                                .addOnFailureListener(e -> {
//                                    Toast.makeText(getApplicationContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show();
//                                });
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Failed to generate cart item ID", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(getApplicationContext(), "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            Toast.makeText(getApplicationContext(), "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
//        }
//    }


//    private void addToCart() {
//        // Get the current user
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // Get user ID
//            String userId = user.getUid();
//
//            // Get reference to the cart_items node for the user
//            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cart_items");
//
//            // Check if item already exists
//            cartRef.orderByChild("price").equalTo(itemPrice).addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        // Item already exists, update quantity and total price
//                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
//                            String cartItemId = itemSnapshot.getKey();
//
//                            int existingQuantity = itemSnapshot.child("quantity").getValue(Integer.class);
//                            int newQuantity = existingQuantity + quantityToAdd;
//                            int newTotalPrice = Integer.parseInt(itemPrice) * newQuantity;
//
////                            int existingQuantity = itemSnapshot.child("quantity").getValue(Integer.class);
////                            int newQuantity = existingQuantity + 1;
////                            int newTotalPrice = Integer.parseInt(itemPrice) * newQuantity;
//
//                            // Update cart item with new quantity and total price
//                            cartRef.child(cartItemId).child("quantity").setValue(newQuantity);
//                            cartRef.child(cartItemId).child("totalPrice").setValue(newTotalPrice)
//                                .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(), "Item quantity updated", Toast.LENGTH_SHORT).show())
//                                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to update cart", Toast.LENGTH_SHORT).show());
//                            break; // Update only the first matching item (unique price)
//                        }
//                    } else {
//                        // Item doesn't exist, add new item as before
//                        String cartItemId = cartRef.push().getKey();
//                        int totQuantity = 1; // Since item is added for the first time
//                        int totPrice = Integer.parseInt(itemPrice) * totQuantity;
//                        CartPageItem cartPageItem = new CartPageItem(itemPrice, totQuantity, totPrice);
//
//                        if (cartItemId != null) {
//                            cartRef.child(cartItemId).setValue(cartPageItem)
//                                .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show())
//                                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show());
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Failed to generate cart item ID", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                    // Handle database errors
//                }
//            });
//        } else {
//            Toast.makeText(getApplicationContext(), "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
//        }
//    }



    private void addToCart() {
        // Get the current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Get user ID
            String userId = user.getUid();

            // Get reference to the cart_items node for the user
            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("cart_items");

            // Check if item already exists
            cartRef.orderByChild("price").equalTo(itemPrice).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        // Item already exists, update quantity and total price
                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                            String cartItemId = itemSnapshot.getKey();

                            // Get the existing quantity from the database
                            int existingQuantity = itemSnapshot.child("quantity").getValue(Integer.class);

                            // Calculate the quantity to add
//                            int quantityToAdd = 1; // Increment by 1 for each addition
                            int newQuantity = existingQuantity + totQuantity;
                            int newTotalPrice = Integer.parseInt(itemPrice) * newQuantity;

                            // Update cart item with new quantity and total price
                            cartRef.child(cartItemId).child("quantity").setValue(newQuantity);
                            cartRef.child(cartItemId).child("totalPrice").setValue(newTotalPrice)
                                .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(), "Cart updated", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to update cart", Toast.LENGTH_SHORT).show());
                            break;
                        }
                    } else {
                        String cartItemId = cartRef.push().getKey();
                        int totPrice = Integer.parseInt(itemPrice) * totQuantity;
                        CartPageItem cartPageItem = new CartPageItem(itemPrice, totQuantity, totPrice);

                        if (cartItemId != null) {
                            cartRef.child(cartItemId).setValue(cartPageItem)
                                .addOnSuccessListener(aVoid -> Toast.makeText(getApplicationContext(), "Item added to cart", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to add item to cart", Toast.LENGTH_SHORT).show());
                        } else {
                            Toast.makeText(getApplicationContext(), "Failed to generate cart item ID", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle database errors
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Please sign in to add items to cart", Toast.LENGTH_SHORT).show();
        }
    }




}