package com.example.android.foodiego;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class OrderHistoryAdapter extends ArrayAdapter<CartPageItem> {

    private Context mContext;
    private List<CartPageItem> mOrders;

    public OrderHistoryAdapter(Context context, List<CartPageItem> orders) {
        super(context, 0, orders);
        mContext = context;
        mOrders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.order_history_item_layout,parent,false);

        CartPageItem currentOrder = mOrders.get(position);

        TextView itemName = listItem.findViewById(R.id.item_name);
        itemName.setText("price: " + currentOrder.getPrice());

        TextView itemQuantity = listItem.findViewById(R.id.item_quantity);
        itemQuantity.setText("Quantity: " + currentOrder.getQuantity());

        TextView totalPrice = listItem.findViewById(R.id.item_tot_price);
        totalPrice.setText("Total Price: " + currentOrder.getTotalPrice());

        return listItem;
    }





}
