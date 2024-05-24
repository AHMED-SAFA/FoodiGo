package com.example.android.foodiego;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginUiTest {

    @Rule
    public ActivityScenarioRule<log_user> activityScenarioRule = new ActivityScenarioRule<>(log_user.class);

    @Test
    public void testLoginSuccess() {
        Espresso.onView(ViewMatchers.withId(R.id.login_email_editText)).perform(ViewActions.typeText("sf@gmail.com"));
        Espresso.onView(ViewMatchers.withId(R.id.login_password_editText)).perform(ViewActions.typeText("123456"));
        Espresso.onView(ViewMatchers.withId(R.id.login_button_id)).perform(ViewActions.click());
    }

    @Test
    public void testLoginFailure() {
        Espresso.onView(ViewMatchers.withId(R.id.login_email_editText)).perform(ViewActions.typeText("sf@gmail.com"));
        Espresso.onView(ViewMatchers.withId(R.id.login_password_editText)).perform(ViewActions.typeText("12345"));
        Espresso.onView(ViewMatchers.withId(R.id.login_button_id)).perform(ViewActions.click());
    }

}
