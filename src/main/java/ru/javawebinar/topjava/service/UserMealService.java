package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * Created by Asset on 10.03.2016.
 */
public interface UserMealService {

    UserMeal save(Integer userId, UserMeal userMeal);

    boolean delete(Integer userId, int id);

    UserMeal get(Integer userId, int id);

    Collection<UserMeal> getAll(Integer userId);
}
