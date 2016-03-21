package ru.javawebinar.topjava.repository;


import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

public interface UserMealRepository {


    UserMeal save(User user, UserMeal userMeal);


    boolean delete(User user,int id);


    UserMeal get(User user, int id);


    Collection<UserMeal> getAll(User user);
}
