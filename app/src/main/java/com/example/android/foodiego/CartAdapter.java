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
