package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface UserMealRepository {

    UserMeal save(int userId, UserMeal userMeal);

    boolean delete(int userId, int id);

    UserMeal get(int userId, int id);

    Collection<UserMeal> getAll(int userId);

    Collection<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
