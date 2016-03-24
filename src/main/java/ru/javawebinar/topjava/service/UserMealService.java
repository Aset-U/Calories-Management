package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

/**
 * Created by Asset on 10.03.2016.
 */
public interface UserMealService {

    UserMeal save(Integer userId, UserMeal userMeal);

    UserMeal update(Integer userId, UserMeal userMeal) throws NotFoundException;

    void delete(Integer userId, int id) throws NotFoundException;

    UserMeal get(Integer userId, int id) throws NotFoundException;

    Collection<UserMeal> getAll(Integer userId);

    Collection<UserMeal> getBetweenDateTimes(LocalDateTime startDateTime, LocalDateTime endDateTime,Integer userId);

    default Collection<UserMeal> getBetweenDates(LocalDate startDate, LocalDate endDate, Integer userId){
        return getBetweenDateTimes(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MAX), userId);
    }
}
