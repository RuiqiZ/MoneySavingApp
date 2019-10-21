package com.rocket.jarapp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.rocket.jarapp.business.AccessExpensesIT;
import com.rocket.jarapp.business.AccessJarsIT;
import com.rocket.jarapp.business.AccessTagsIT;
import com.rocket.jarapp.business.UpdateExpensesIT;
import com.rocket.jarapp.business.UpdateJarsIT;
import com.rocket.jarapp.business.UpdateTagsIT;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccessJarsIT.class,
        AccessExpensesIT.class,
        AccessTagsIT.class,
        UpdateExpensesIT.class,
        UpdateJarsIT.class,
        UpdateTagsIT.class
})
public class AllIntegrationTests {
}

