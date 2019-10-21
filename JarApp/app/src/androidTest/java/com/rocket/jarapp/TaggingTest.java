package com.rocket.jarapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rocket.jarapp.business.AccessTags;
import com.rocket.jarapp.presentation.MainActivity;
import com.rocket.jarapp.utils.TestUtils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;

//Create new tags
//Associate/unassociate a tag with/from an expense

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TaggingTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createNewTagSuccessful() {
        String newTag = new TestUtils().generateUniqueName();

        // Open the first expense
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Type in the new tag's name and click create
        onView(withId(R.id.tb_expense_new_tag)).perform(typeText(newTag));
        onView(withId(R.id.btn_expense_create_tag)).perform(click());

        // open the tags dialog to see if the new tag is in there
        onView(withId(R.id.btn_expense_select_tags)).perform(click());
        onView(withText(newTag)).check(matches(isDisplayed()));
        onView(withText("CANCEL")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());

        // Update the expense
        onView(withId(R.id.edit_expense_update)).perform(click());

        // Reopen the expense, and confirm the tag is still being displayed
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.btn_expense_select_tags)).perform(click());
        onView(withText(newTag)).check(matches(isDisplayed()));
        onView(withText("CANCEL")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void createNewTagUnsuccessful() {
        // Get the first tag in the system
        String existingTagName = new AccessTags().getAllTagNames().get(0);

        // open up the first expense
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Type in the existing tag and attempt to add it
        onView(withId(R.id.tb_expense_new_tag)).perform(typeText(existingTagName));
        onView(withId(R.id.btn_expense_create_tag)).perform(click());

        // Check whether a toast appeared
        onView(withText("Tag already exists"))
                .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
    }

}
