package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.UserMealsUtil;


public class LoggedUser {
    public static int id(){
        return 1;
    }

    public static int getCaloriesPerDay(){
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
