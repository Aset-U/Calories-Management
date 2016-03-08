package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.UserMealsUtil;

/**
 * Created by Asset on 08.03.2016.
 */
public class LoggedUser {
    public static int id(){
        return 1;
    }

    public static int getCaloriesPerDay(){
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
