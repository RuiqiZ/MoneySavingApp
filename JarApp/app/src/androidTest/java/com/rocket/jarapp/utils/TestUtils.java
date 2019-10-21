package com.rocket.jarapp.utils;

import com.rocket.jarapp.business.AccessExpenses;
import com.rocket.jarapp.business.AccessJars;
import com.rocket.jarapp.business.AccessTags;
import com.rocket.jarapp.business.UpdateExpenses;
import com.rocket.jarapp.business.UpdateJars;
import com.rocket.jarapp.objects.Expense;
import com.rocket.jarapp.objects.Jar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestUtils {

    private AccessExpenses accessExpenses;
    private AccessJars accessJars;
    private UpdateExpenses updateExpenses;
    private UpdateJars updateJars;

    public TestUtils() {
        accessExpenses = new AccessExpenses();
        accessJars = new AccessJars();

        updateExpenses = new UpdateExpenses();
        updateJars = new UpdateJars();
    }

    public Jar getJarByPosition(int pos) {
        return  accessJars.getAllJars().get(pos);
    }

    public int getJarSize() {
        return accessJars.getAllJars().size();
    }

    public String generateUniqueName() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public String generateCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public String generateCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
