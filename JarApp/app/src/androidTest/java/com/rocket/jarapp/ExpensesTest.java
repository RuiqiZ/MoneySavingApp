package com.rocket.jarapp;

import android.os.SystemClock;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.rocket.jarapp.objects.Expense;
import com.rocket.jarapp.objects.Jar;
import com.rocket.jarapp.presentation.MainActivity;
import com.rocket.jarapp.utils.TestUtils;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExpensesTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private TestUtils testUtils;

    @Before
    public void setupTestUtils() {
        testUtils = new TestUtils();
    }


    @Test
    public void createAndJarAssociateExpense() {
        onView(withId(R.id.fab_add_expense)).perform(click());

        String expenseName = testUtils.generateUniqueName();
        String expenseAmount = "30.5";

        String[] splittedDate = testUtils.generateCurrentDate().split("/");
        int day = Integer.valueOf(splittedDate[0]);
        int month = Integer.valueOf(splittedDate[1]);
        int year = Integer.valueOf(splittedDate[2]);

        String expenseTime = testUtils.generateCurrentTime();
        String[] splittedTime = expenseTime.split(":");
        int hour = Integer.valueOf(splittedTime[0]);
        int minute = Integer.valueOf(splittedTime[1]);

        // Add the new expense
        onView(withId(R.id.tb_expense_name)).perform(typeText(expenseName));
        onView(withId(R.id.tb_expense_price)).perform(typeText(expenseAmount));

        onView(withId(R.id.tb_expense_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.tb_expense_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minute));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.create_expense_save)).perform(click());

        // Verify that it was added and associated with current jar
        onView(withText(expenseName)).perform(click());
        onView(withId(R.id.tb_expense_name)).check(matches(withText(expenseName)));
        onView(withId(R.id.tb_expense_price)).check(matches(withText(expenseAmount)));
        onView(withId(R.id.tb_expense_date)).check(matches(withText(day + "/" + month + "/" + year)));
        onView(withId(R.id.tb_expense_time)).check(matches(withText(expenseTime)));
    }

    @Test
    public void editExpense() {
        String newName = testUtils.generateUniqueName();
        String newAmount = "55.5";

        String[] splittedDate = testUtils.generateCurrentDate().split("/");
        int day = Integer.valueOf(splittedDate[0]);
        int month = Integer.valueOf(splittedDate[1]);
        int year = Integer.valueOf(splittedDate[2]);

        String newTime = testUtils.generateCurrentTime();
        String[] splittedTime = newTime.split(":");
        int hour = Integer.valueOf(splittedTime[0]);
        int minute = Integer.valueOf(splittedTime[1]);

        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tb_expense_name)).perform(clearText());
        onView(withId(R.id.tb_expense_name)).perform(typeText(newName));
        onView(withId(R.id.tb_expense_price)).perform(clearText());
        onView(withId(R.id.tb_expense_price)).perform(typeText(newAmount));

        onView(withId(R.id.tb_expense_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, month, day));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.tb_expense_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minute));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.edit_expense_update)).perform(click());

        // Verify that new information has been updated
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tb_expense_name)).check(matches(withText(newName)));
        onView(withId(R.id.tb_expense_price)).check(matches(withText(newAmount)));
        onView(withId(R.id.tb_expense_date)).check(matches(withText(day + "/" + month + "/" + year)));
        onView(withId(R.id.tb_expense_time)).check(matches(withText(newTime)));
    }

    @Test
    public void deleteExpense() {
        Jar jar = testUtils.getJarByPosition(0);
        Expense expense = jar.getExpenses().get(jar.getExpenses().size() - 1);

        // Delete last expense in the jar
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(jar.getExpenses().size() - 1, click()));
        onView(withId(R.id.edit_expense_delete)).perform(click());

        onView(withText(expense.getName())).check(doesNotExist());
    }

    @Test
    public void addExpenseNote() {
        String newNote = testUtils.generateUniqueName();

        // Open the first expense and add a note
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tb_expense_note)).perform(clearText());
        onView(withId(R.id.tb_expense_note)).perform(typeText(newNote));
        closeSoftKeyboard();

        onView(withId(R.id.edit_expense_update)).perform(click());

        // Re-open the first expense to check if the note is correct
        onView(allOf(withId(R.id.jar_layout_recycler), isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tb_expense_note)).check(matches(withText(newNote)));
    }

    @Test
    public void allExpenses() {
        // Open all expenses page to see current expenses
        onView(withId(R.id.task_see_all_expenses)).perform(click());
        SystemClock.sleep(500);
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.fab_add_expense)).perform(click());

        // Add a new expense
        String expenseName = testUtils.generateUniqueName();
        onView(withId(R.id.tb_expense_name)).perform(typeText(expenseName));
        onView(withId(R.id.tb_expense_price)).perform(typeText("22.6"));

        onView(withId(R.id.tb_expense_date)).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.tb_expense_time)).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.create_expense_save)).perform(click());

        // Re-open all expenses page to check the new expense show up there
        onView(withId(R.id.task_see_all_expenses)).perform(click());
        onView(withId(R.id.all_expenses_layout_recycler)).perform(swipeUp());
        SystemClock.sleep(500);
        onView(withText(expenseName)).check(matches(isDisplayed()));
    }

}
