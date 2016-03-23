package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.util.Collection;

/**
 * Created by Asset on 10.03.2016.
 */
@Repository
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        return repository.save(userId, userMeal);
    }

    @Override
    public boolean delete(Integer userId, int id) {
        return repository.delete(userId, id);
    }

    @Override
    public UserMeal get(Integer userId, int id) {
        return repository.get(userId, id);
    }

    @Override
    public Collection<UserMeal> getAll(Integer userId) {
        return repository.getAll(userId);
    }
}
