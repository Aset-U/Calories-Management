package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.ExceptionUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;


@Repository
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;

    @Override
    public UserMeal save(Integer userId, UserMeal userMeal) {
        return repository.save(userId, userMeal);
    }

    @Override
    public UserMeal update(Integer userId, UserMeal userMeal) throws NotFoundException {
            return ExceptionUtil.check(repository.save(userId, userMeal), userMeal.getId());
    }

    @Override
    public void delete(Integer userId, int id) {
        ExceptionUtil.check(repository.delete(userId, id), id);
    }

    @Override
    public UserMeal get(Integer userId, int id) {
        return ExceptionUtil.check(repository.get(userId, id), id);
    }

    @Override
    public Collection<UserMeal> getAll(Integer userId) {
        return repository.getAll(userId);
    }

    @Override
    public Collection<UserMeal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer userId) {
        return repository.getBetween(startDateTime, endDateTime, userId);
    }
}
