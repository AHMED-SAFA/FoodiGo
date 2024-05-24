package com.example.android.foodiego;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    List<CartPageItem> cartItems;
    private TextView totalAmountTv;
    int itemTotalPrice = 0;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        navigationView = findViewById(R.id.nav_view_id);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        toolbar = findViewById(R.id.toolbar2);
        recyclerView = findViewById(R.id.recycler_view_cart);
        button = findViewById(R.id.buy_btn_id);
        totalAmountTv = findViewById(R.id.tot_Amount);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_item_home_id)
            {
                startActivity(new Intent(this, home.class));
            }
            if (item.getItemId() == R.id.nav_prev_order_id)
            {
                startActivity(new Intent(this, prevOrder.class));
            }
            if (item.getItemId() == R.id.nav_item_cart_id )
            {
                startActivity(new Intent(this, Cart.class));
                finish();
            }

            if (item.getItemId() == R.id.nav_item_logout_id)
            {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, user.class);
                startActivity(intent);
                finish();
            }
            return false;
        });

        cartItems = new ArrayList<>();

        fetchCartItems();
        button.setOnClickListener(v -> {
            orderConfirmed();
        });
    }

    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate(R.menu.nav_items, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.nav_item_home_id)
        {
            startActivity(new Intent(this, home.class));
        }
        if (item.getItemId() == R.id.nav_item_cart_id )
        {
            startActivity(new Intent(this, Cart.class));
            finish();
        }
        if (item.getItemId() == R.id.nav_item_logout_id)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, user.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
    void fetchCartItems() {
        // Get current user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Reference to the current user's cart items
            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users")
                    .child(userId).child("cart_items");

            cartRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    cartItems.clear();
                    itemTotalPrice = 0;
                    for (DataSnapshot cartItemSnapshot : dataSnapshot.getChildren()) {
                        // Get cart item details
                        String price = cartItemSnapshot.child("price").getValue(String.class);
                        int quantity = cartItemSnapshot.child("quantity").getValue(Integer.class);
                        int totalPrice = cartItemSnapshot.child("totalPrice").getValue(Integer.class);
                        String currentDate = cartItemSnapshot.child("currentDate").getValue(String.class);
                        String currentTime = cartItemSnapshot.child("currentTime").getValue(String.class);

                        // Increment total price
                        itemTotalPrice += totalPrice;

                        // Create CartPageItem object
                        CartPageItem cartPageItem = new CartPageItem(price, quantity, totalPrice);
                        cartPageItem.setCurrentDate(currentDate);
                        cartPageItem.setCurrentTime(currentTime);

                        // Add cart item to list
                        cartItems.add(cartPageItem);
                    }
                    // Update RecyclerView
                    cartAdapter = new CartAdapter(cartItems);
                    recyclerView.setAdapter(cartAdapter);

                    // Update total amount TextView
                    totalAmountTv.setText("Total Price: " + itemTotalPrice + " $");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Cart.this, "Failed to retrieve cart items", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void orderConfirmed() {

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty. Add items to place an order.", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Reference to the current user's cart items
            DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference("users")
                    .child(userId).child("cart_items");

            // Reference to the current user's order history
            DatabaseReference orderHistoryRef = FirebaseDatabase.getInstance().getReference("users")
                    .child(userId).child("order_history");

            // Get a unique key for the order
            String orderId = orderHistoryRef.push().getKey();

            // Iterate through the cart items
            for (CartPageItem cartItem : cartItems) {
                // Push the cart item to the order history with a unique key
                String cartItemId = orderHistoryRef.push().getKey();
                orderHistoryRef.child(cartItemId).setValue(cartItem.toMap());
            }

            // Remove all items from the cart
            cartRef.removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(Cart.this, "Order placed successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Cart.this, "Failed to place order", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

