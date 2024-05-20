//package com.example.android.foodiego;
//
//import android.annotation.SuppressLint;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
//
//    private List<CartPageItem> cartItems;
//
//    public CartAdapter(List<CartPageItem> cartItems) {
//        this.cartItems = cartItems;
//    }
//
//    @NonNull
//    @Override
//    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
//        return new CartViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
//        CartPageItem cartItem = cartItems.get(position);
//        holder.bind(cartItem);
//    }
//
//    @Override
//    public int getItemCount() {
//        return cartItems.size();
//    }
//
//    public static class CartViewHolder extends RecyclerView.ViewHolder {
//
//        TextView productPriceTextView;
//        TextView currentDateTextView;
//        TextView currentTimeTextView;
//        TextView totalQuantityTextView;
//        TextView totalPriceTextView;
//
//        public CartViewHolder(@NonNull View itemView) {
//            super(itemView);
//            productPriceTextView = itemView.findViewById(R.id.product_price);
//            currentDateTextView = itemView.findViewById(R.id.current_date);
//            currentTimeTextView = itemView.findViewById(R.id.current_time);
//            totalQuantityTextView = itemView.findViewById(R.id.tot_quantity);
//            totalPriceTextView = itemView.findViewById(R.id.tot_price);
//        }
//
//        @SuppressLint("SetTextI18n")
//        public void bind(CartPageItem cartItem) {
//            productPriceTextView.setText("Product Price: " + cartItem.getPrice());
//            currentDateTextView.setText("Ordered Date: " + cartItem.getCurrentDate());
//            currentTimeTextView.setText("Ordered Time: " + cartItem.getCurrentTime());
//            totalQuantityTextView.setText("Total Quantity: " + cartItem.getQuantity());
//            totalPriceTextView.setText("Total Price: " + cartItem.getTotalPrice());
//        }
//    }
//}
//
//
//
//
//
//
//
////public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
////    public List<CartPageItem> cartItems;
////    public CartAdapter(List<CartPageItem> cartItems) {
////        this.cartItems = cartItems;
////    }
////    @NonNull
////    @Override
////    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
////        return new CartViewHolder(view);
////    }
////
////    static class CartViewHolder extends RecyclerView.ViewHolder {
////        TextView productPrice, currentDate, currentTime, totQuantity, totPrice;
////
////        public CartViewHolder(@NonNull View itemView) {
////            super(itemView);
////            productPrice = itemView.findViewById(R.id.product_price);
////            currentDate = itemView.findViewById(R.id.current_date);
////            currentTime = itemView.findViewById(R.id.current_time);
////            totQuantity = itemView.findViewById(R.id.tot_quantity);
////            totPrice = itemView.findViewById(R.id.tot_price);
////        }
////    }
////    @Override
////    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
////        CartPageItem cartItem = cartItems.get(position);
////
////        holder.productPrice.setText(cartItem.getPrice());
////        holder.currentDate.setText(cartItem.getCurrentDate());
////        holder.currentTime.setText(cartItem.getCurrentTime());
////        holder.totQuantity.setText(String.valueOf(cartItem.getQuantity()));
////        holder.totPrice.setText(String.valueOf(cartItem.getTotalPrice()));
////    }
////    @Override
////    public int getItemCount() {
////        return cartItems.size();
////    }
////}



// CartAdapter.java
package com.example.android.foodiego;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<CartPageItem> cartItems;

    public CartAdapter(Context context, List<CartPageItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartPageItem cartItem = cartItems.get(position);
        holder.tvItemPrice.setText(cartItem.getPrice());
        holder.tvItemQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.tvTotalPrice.setText(String.valueOf(cartItem.getTotalPrice()));
        holder.currentDate.setText(String.valueOf(cartItem.getCurrentDateDatabase()));
        holder.currentTime.setText(String.valueOf(cartItem.getCurrentTimeDatabase()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemPrice, tvItemQuantity, tvTotalPrice,currentDate,currentTime;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemPrice = itemView.findViewById(R.id.tv_item_price);
            tvItemQuantity = itemView.findViewById(R.id.tv_item_quantity);
            tvTotalPrice = itemView.findViewById(R.id.tv_total_price);
            currentDate = itemView.findViewById(R.id.current_date);
            currentTime = itemView.findViewById(R.id.current_time);
        }
    }
}

