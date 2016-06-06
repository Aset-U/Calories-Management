package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.UserMealsUtil;


public class LoggedUser {

    public static int id = 1;

    public static int id(){
        return id;
    }

    public static void setId(int id){
        LoggedUser.id = id;
    }

    public static int getCaloriesPerDay(){
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
