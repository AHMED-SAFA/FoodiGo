package com.example.android.foodiego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class prevOrder extends AppCompatActivity {

    ListView listView;
    ArrayList<String> orderList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prev_order);

        listView = findViewById(R.id.listView);

        fetchOrderHistory();
        }

    private void fetchOrderHistory() {

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userId = currentUser.getUid();
        DatabaseReference orderHistoryRef = FirebaseDatabase.getInstance().getReference("users")
            .child(userId).child("order_history");

        orderHistoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CartPageItem> orderHistory = new ArrayList<>();
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {

                    String price = orderSnapshot.child("price").getValue(String.class);
                    int quantity = orderSnapshot.child("quantity").getValue(Integer.class);
                    int totalPrice = orderSnapshot.child("totalPrice").getValue(Integer.class);

                    CartPageItem orderItem = new CartPageItem(price, quantity, totalPrice);
                    orderHistory.add(orderItem);
                }

                OrderHistoryAdapter adapter = new OrderHistoryAdapter(prevOrder.this, orderHistory);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(prevOrder.this, "Failed to retrieve order history", Toast.LENGTH_SHORT).show();
            }
        });
    }
}











//    private void fetchOrderHistory() {
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser != null) {
//            String userId = currentUser.getUid();
//            DatabaseReference orderHistoryRef = FirebaseDatabase.getInstance().getReference("users")
//                    .child(userId).child("order_history");
//
//            orderHistoryRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        OrderHistoryItemClass orderHistoryItemClass = snapshot.getValue(OrderHistoryItemClass.class);
//                        orderHistoryList.add(orderHistoryItemClass);
//                    }
//                    populateListView(orderHistoryList);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Handle error
//                }
//            });
//        }
//    }
//
//    private void populateListView(List<OrderHistoryItemClass> orderHistoryItemClassList) {
//        ArrayAdapter<OrderHistoryItemClass> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1, orderHistoryItemClassList);
//        listView.setAdapter(adapter);
//    }