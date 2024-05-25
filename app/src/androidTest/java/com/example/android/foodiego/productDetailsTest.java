package com.example.android.foodiego;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class productDetailsTest {

    @Rule
    public ActivityScenarioRule<productDetails> activityRule =
        new ActivityScenarioRule<>(productDetails.class);

    @Test
    public void testInitialQuantity() {
        // Verify quantity
        Espresso.onView(ViewMatchers.withId(R.id.quantity))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")));
    }

    @Test
    public void testAddToCartButton() {
        // Click on add to cart button
        Espresso.onView(ViewMatchers.withId(R.id.addtocart)).perform(ViewActions.click());
    }

}

