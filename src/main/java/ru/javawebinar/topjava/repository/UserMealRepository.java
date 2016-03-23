package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

public interface UserMealRepository {

    UserMeal save(Integer userId, UserMeal userMeal);

    boolean delete(Integer userId, int id);

    UserMeal get(Integer userId, int id);

    Collection<UserMeal> getAll(Integer userId);
}
