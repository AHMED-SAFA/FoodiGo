package com.example.android.foodiego;

import android.content.Intent;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class itemAdapter extends BaseAdapter {
    Context c;
    int items[];
    String items_price[];
    String items_desc[];

    itemAdapter(Context c, int arr[],String desc[],String price[]) {
        this.c = c;
        items = arr;
        items_desc = desc;
        items_price = price;
    }
    @Override public int getCount() { return items.length; }
    @Override public Object getItem(int i) { return null; }
    @Override public long getItemId(int i) { return 0; }
    @Override public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.display_item, null);
        }
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.descriptionTextView);
        TextView price = view.findViewById(R.id.price_id);

        imageView.setImageResource(items[i]);
        textView.setText(items_desc[i]);
        price.setText(items_price[i]+" $");

        // Set click listener
        view.setOnClickListener(v -> {
            Intent intent = new Intent(c, productDetails.class);
            intent.putExtra("item_image", items[i]);
            intent.putExtra("item_description", items_desc[i]);
            intent.putExtra("item_price", items_price[i]);
            c.startActivity(intent);
        });

        return view;
    }
}
