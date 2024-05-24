package com.example.android.foodiego;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CartPageItem {

    public String price;
    public int quantity;
    public int totalPrice;
    public String currentDate;
    public String currentTime;

    public CartPageItem(String price, int quantity, int totalPrice) {
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.currentDate = getCurrentDate();
        this.currentTime = getCurrentTime();
    }

    public CartPageItem(String price, int quantity, int totalPrice, String date, String time) {
    }

    public CartPageItem(double v, int quantity, double v1) {
    }

    public String getCurrentDate() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public String getCurrentDateDatabase() {
        return currentDate;
    }
    public String getCurrentTimeDatabase() {
        return currentTime;
    }

    public String getCurrentTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return timeFormat.format(date);
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("price", price);
        result.put("quantity", quantity);
        result.put("totalPrice", totalPrice);
        return result;
    }

}
