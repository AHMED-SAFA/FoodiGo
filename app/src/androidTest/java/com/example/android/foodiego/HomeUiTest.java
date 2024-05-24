package com.example.android.foodiego;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.core.IsAnything.anything;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class HomeUiTest {
    @Rule
    public ActivityScenarioRule<home> activityScenarioRule = new ActivityScenarioRule<>(home.class);
    @Test
    public void testViewExists() {
        // Check if the grid view exists
        Espresso.onView(withId(R.id.grid_view))
            .check(matches(isDisplayed()));
    }
    @Test
    public void scrollViewExists() {
        // Scroll in the ScrollView
        Espresso.onView(withId(R.id.scroll_view))
            .perform(ViewActions.swipeUp());
    }
    @Test
    public void testGridViewItemClickAt0() {
        // Click on an item in the GridView
        //at position 0
        Espresso.onData(anything())
            .inAdapterView(withId(R.id.grid_view))
            .atPosition(0)
            .perform(ViewActions.click());
    }
    @Test
    public void testGridViewItemClickAt1() {        // Click on an item in the GridView
        //at position 1
        Espresso.onData(anything())
            .inAdapterView(withId(R.id.grid_view))
            .atPosition(1)
            .perform(ViewActions.click());
    }
}
