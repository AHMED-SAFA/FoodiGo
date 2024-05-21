package com.example.android.foodiego;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    GridView gridView;

    int[] img_itemsarray = new int[]{
            R.drawable.ban1, R.drawable.ban3,
        R.drawable.ban2, R.drawable.ban4
    };
    String[] price_itemsarray = new String[]{
        "20", "30",
        "5", "25",
    };
    String[] desc_itemsarray = new String[]{
        "Mixed Fried Meat", "Pizza",
        "Vegetarian Salad", "Platter"
    };

    private final List<HomeObserver> observers = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gridView = findViewById(R.id.grid_view);
        navigationView = findViewById(R.id.nav_view_id);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        toolbar = findViewById(R.id.toolbar_id);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // create a object of itemAdapter
        itemAdapter itemAdapter = new itemAdapter(this, img_itemsarray, desc_itemsarray, price_itemsarray);
        gridView.setAdapter(itemAdapter);

        navigationView.setNavigationItemSelectedListener(item -> {
            handleNavigationItemSelected(item);
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_items, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        handleNavigationItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void handleNavigationItemSelected(MenuItem item) {
        notifyObservers(item);
        if (item.getItemId() == R.id.nav_item_home_id) {
            startActivity(new Intent(this, home.class));
            finish();
        } else if (item.getItemId() == R.id.nav_prev_order_id) {
            startActivity(new Intent(this, prevOrder.class));
        } else if (item.getItemId() == R.id.nav_item_cart_id) {
            startActivity(new Intent(this, Cart.class));
        } else if (item.getItemId() == R.id.nav_item_logout_id) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, user.class);
            startActivity(intent);
            finish();
        }
    }

    public void addObserver(HomeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(HomeObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(MenuItem item) {
        for (HomeObserver observer : observers) {
            observer.onNavigationItemSelected(item);
        }
    }
}