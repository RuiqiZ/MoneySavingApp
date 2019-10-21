package com.rocket.jarapp;

import com.rocket.jarapp.objects.Expense;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TaggingTest.class,
        ExpensesTest.class,
        JarsTest.class
})

public class AllSystemTests {
}
