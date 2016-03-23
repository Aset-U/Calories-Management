package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import java.util.Collection;

/**
 * Created by Asset on 10.03.2016.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealServiceImpl service;

    public UserMeal save(Integer userId, UserMeal meal) {
        return service.save(userId, meal);
    }

    public boolean delete(Integer userId, int id){
        return service.delete(userId, id);
    }

    public UserMeal get(Integer userId, int id) {
        return service.get(userId, id);
    }

    public Collection<UserMeal> getAll(Integer userId) {
        return service.getAll(userId);
    }
}
