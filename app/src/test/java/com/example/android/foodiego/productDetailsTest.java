package com.example.android.foodiego;

import android.content.Intent;
import org.junit.runner.RunWith;
import androidx.test.core.app.ActivityScenario;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class productDetailsTest {

    private static final String ITEM_PRICE = "10";

    private Intent createIntent() {
        Intent intent = new Intent();
        intent.putExtra("item_image", R.drawable.ban1);  // Assuming you have a sample image resource
        intent.putExtra("item_name", "Test Item");
        intent.putExtra("item_price", ITEM_PRICE);
        intent.putExtra("item_description", "This is a test item.");
        return intent;
    }

    @Test
    public void testAddQuantity_ValidInput_IncrementsQuantity() {
        try (ActivityScenario<productDetails> scenario = ActivityScenario.launch(createIntent())) {
            scenario.onActivity(activity -> {
                activity.totQuantity = 2;
                activity.itemPrice = ITEM_PRICE;

                activity.findViewById(R.id.add_item).performClick();

                assertEquals(3, activity.totQuantity);
                assertEquals(Integer.parseInt(activity.itemPrice) * 3, activity.totPrice);
            });
        }
    }

    @Test
    public void testRemoveQuantity_ValidInput_DecrementsQuantity() {
        try (ActivityScenario<productDetails> scenario = ActivityScenario.launch(createIntent())) {
            scenario.onActivity(activity -> {
                activity.totQuantity = 5;
                activity.itemPrice = ITEM_PRICE;

                activity.findViewById(R.id.minus_item).performClick();

                assertEquals(4, activity.totQuantity);
                assertEquals(Integer.parseInt(activity.itemPrice) * 4, activity.totPrice);
            });
        }
    }

    @Test
    public void testAddQuantity_MaxLimit_DoesntExceedLimit() {
        try (ActivityScenario<productDetails> scenario = ActivityScenario.launch(createIntent())) {
            scenario.onActivity(activity -> {
                activity.totQuantity = 10;
                activity.itemPrice = ITEM_PRICE;

                activity.findViewById(R.id.add_item).performClick();

                assertEquals(10, activity.totQuantity); // Doesn't go beyond 10
            });
        }
    }

    @Test
    public void testRemoveQuantity_MinLimit_DoesntGoBelowZero() {
        try (ActivityScenario<productDetails> scenario = ActivityScenario.launch(createIntent())) {
            scenario.onActivity(activity -> {
                activity.totQuantity = 0;
                activity.itemPrice = ITEM_PRICE;

                activity.findViewById(R.id.minus_item).performClick();

                assertEquals(0, activity.totQuantity); // Doesn't go negative
            });
        }
    }
}
