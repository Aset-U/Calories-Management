package ru.javawebinar.topjava.repository.jpa;


import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public class JpaUserMealRepositoryImpl implements UserMealRepository{


    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        return null;
    }

    @Override
    public boolean delete(int userId, int id) {
        return false;
    }

    @Override
    public UserMeal get(int userId, int id) {
        return null;
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        return null;
    }

    @Override
    public Collection<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
