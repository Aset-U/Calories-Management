package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Asset on 10.03.2016.
 */
@Controller
public class UserMealRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserMealRestController.class);

    @Autowired
    private UserMealServiceImpl service;

    public UserMeal get(int id) {
        int userId = LoggedUser.id();
        LOG.info("get meal {} for User {}", id, userId);
        return service.get(userId, id);
    }

    public UserMeal create(UserMeal meal) {
        meal.setId(null);
        int userId = LoggedUser.id();
        LOG.info("create meal {} for User {}", meal, userId);
        return service.save(userId, meal);
    }

    public UserMeal update(UserMeal meal, int id) {
        meal.setId(id);
        int userId = LoggedUser.id();
        LOG.info("update meal {} for User {}", meal, userId);
        return service.update(userId, meal);
    }

    public void delete(int id){
        int userId = LoggedUser.id();
        LOG.info("delete meal {} for User {}", id, userId);
        service.delete(userId, id);
    }

    public List<UserMealWithExceed> getAll() {
        int userId = LoggedUser.id();
        LOG.info("getAll for User {}", userId);
        return UserMealsUtil.getWithExceeded(service.getAll(userId), LoggedUser.getCaloriesPerDay());
    }

    public List<UserMealWithExceed> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = LoggedUser.id();
        LOG.info("getBetween dates {} - {} for time {} - {} for User", startDate, endDate, startTime, endTime, userId);
        return UserMealsUtil.getFilteredMealsWithExceededByStream(
                service.getBetweenDates(
                        startDate != null ? startDate : TimeUtil.MAX_DATE, endDate != null ? endDate : TimeUtil.MAX_DATE, userId),
                        startTime != null ? startTime : LocalTime.MIN, endTime != null ? endTime : LocalTime.MAX, LoggedUser.getCaloriesPerDay());
    }
}