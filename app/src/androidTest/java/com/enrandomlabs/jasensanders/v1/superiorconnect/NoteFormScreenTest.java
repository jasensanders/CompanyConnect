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
public class NoteFormScreenTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnConnectThenNoteCard(){

        // Click on "Connect" button
        onView(withId(R.id.navigation_dashboard)).perform(click());

        // Verify Connect Fragment is displayed
        onView(withId(R.id.connectScreenLayout)).check(matches(isDisplayed()));

        // Click on "Send Janitors Note" card
        onView(withId(R.id.cardviewNote)).perform(click());

        // Verify Note Fragment is displayed
        onView(withId(R.id.noteFormLayout)).check(matches(isDisplayed()));


    }
}
