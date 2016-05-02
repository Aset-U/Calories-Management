package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.Collection;

public interface UserMealRepository {
    //Вернет null если при обнавлении meal не связан с userId
    UserMeal save(int userId, UserMeal userMeal);

    //Вернет false если  meal не связан с userId
    boolean delete(int userId, int id);

    //Вернет null если meal не связан с userId
    UserMeal get(int userId, int id);

    Collection<UserMeal> getAll(int userId);

    Collection<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
