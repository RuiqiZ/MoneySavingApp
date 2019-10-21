package com.rocket.jarapp;

import android.os.SystemClock;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import com.rocket.jarapp.objects.Jar;
import com.rocket.jarapp.presentation.MainActivity;
import com.rocket.jarapp.utils.TestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class JarsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    private TestUtils testUtils;
    private int sizeOfJars;

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
        sizeOfJars = testUtils.getJarSize();
    }

    @Test
    public void createJar() {
        onView(withId(R.id.task_menu_add_jar)).perform(click());

        // add a new Jar
        onView(withId(R.id.jar_name_text)).perform(typeText("Furniture"));
        onView(withId(R.id.jar_budget_text)).perform(typeText("1750"));
        onView(withId(R.id.jar_theme_spinner)).perform(click());
        onView(withText("yellow")).perform(click());

        closeSoftKeyboard();
        onView(withId(R.id.create_jar_button)).perform(click());

        // verify the new Jar was added
        swipeToLastPage(sizeOfJars + 1);

        onView(allOf(withId(R.id.jar_layout_name), isDisplayed())).check(matches(withText("Furniture")));
        onView(allOf(withId(R.id.jar_layout_budget), isDisplayed())).check(matches(withText("1750.0 / 1750.0")));
    }

    @Test
    public void editJar() {
        onView(allOf(withId(R.id.jar_layout_edit), isDisplayed())).perform(click());

        // edit Jar entries
        onView(withId(R.id.jar_name_text)).perform(clearText(), typeText("MyUtilities"));
        closeSoftKeyboard();
        onView(withId(R.id.edit_jar_save_jar_button)).perform(click());

        // verity updated entries
        onView(allOf(withId(R.id.jar_layout_name), isDisplayed())).check(matches(withText("MyUtilities")));
    }

    @Test
    public void deleteJar() {
        Jar jar = testUtils.getJarByPosition(testUtils.getJarSize() - 1);
        swipeToLastPage(sizeOfJars);

        SystemClock.sleep(500);
        onView(allOf(withId(R.id.jar_layout_edit), isDisplayed())).perform(click());
        closeSoftKeyboard();

        onView(withId(R.id.edit_jar_delete_jar_button)).perform(click());

        // verity Jar has been deleted
        onView(allOf(withId(R.id.jar_layout_name), isDisplayed())).check((matches(not(withText(jar.getName())))));
    }

    @Test
    public void showStats() {
        onView(withId(R.id.task_menu_stats)).perform(click());
        SystemClock.sleep(500);
    }

    public void swipeToLastPage(int lastPage) {
        int i = 1;
        while (i < lastPage) {
            onView(withId(R.id.home_view_pager)).perform(swipeLeft());
            i++;
        }
    }
}
