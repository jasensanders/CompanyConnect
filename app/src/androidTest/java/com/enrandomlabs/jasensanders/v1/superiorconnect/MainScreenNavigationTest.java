package com.enrandomlabs.jasensanders.v1.superiorconnect;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainScreenNavigationTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnHomeNavButton(){

        // Click on "Home" button
        onView(withId(R.id.navigation_home)).perform(click());

        // Verify Main Fragment is displayed
        onView(withId(R.id.mainScreenLayout)).check(matches(isDisplayed()));


    }

    @Test
    public void clickOnConnectNavButton(){

        // Click on "Connect" button
        onView(withId(R.id.navigation_dashboard)).perform(click());

        // Verify Main Fragment is displayed
        onView(withId(R.id.connectScreenLayout)).check(matches(isDisplayed()));

    }

    @Test
    public void clickOnInfoNavButton(){

        // Click on "Information" button
        onView(withId(R.id.navigation_notifications)).perform(click());

        // Verify Main Fragment is displayed
        onView(withId(R.id.infoScreenLayout)).check(matches(isDisplayed()));

    }
}
