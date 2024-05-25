package com.example.android.foodiego;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PrevOrderActivityTest {

    @Rule
    public ActivityScenarioRule<prevOrder> activityScenarioRule = new ActivityScenarioRule<>(prevOrder.class);

    @Test
    public void testOrderHistoryDisplayed() {

        Espresso.onView(withId(R.id.listView))
            .check(matches(isDisplayed()));
    }
}
