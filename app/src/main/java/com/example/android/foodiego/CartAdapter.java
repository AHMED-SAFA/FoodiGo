package com.example.android.foodiego;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartPageItem> cartItems;

    public CartAdapter(List<CartPageItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartPageItem cartItem = cartItems.get(position);
        holder.bind(cartItem);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView productPriceTextView;
        TextView currentDateTextView;
        TextView currentTimeTextView;
        TextView totalQuantityTextView;
        TextView totalPriceTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            currentDateTextView = itemView.findViewById(R.id.current_date);
            currentTimeTextView = itemView.findViewById(R.id.current_time);
            totalQuantityTextView = itemView.findViewById(R.id.tot_quantity);
            totalPriceTextView = itemView.findViewById(R.id.tot_price);
        }

        @SuppressLint("SetTextI18n")
        public void bind(CartPageItem cartItem) {
            productPriceTextView.setText("Product Price: " + cartItem.getPrice());
            currentDateTextView.setText("Ordered Date: " + cartItem.getCurrentDate());
            currentTimeTextView.setText("Ordered Time: " + cartItem.getCurrentTime());
            totalQuantityTextView.setText("Total Quantity: " + cartItem.getQuantity());
            totalPriceTextView.setText("Total Price: " + cartItem.getTotalPrice());
        }
    }
}
