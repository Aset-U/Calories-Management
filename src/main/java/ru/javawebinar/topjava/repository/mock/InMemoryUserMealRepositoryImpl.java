package ru.javawebinar.topjava.repository.mock;

import javafx.print.Collation;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    public static final Comparator<UserMeal> USER_MEAL_COMPARATOR = (um1, um2) -> um2.getDateTime().compareTo(um1.getDateTime());

    private Map<User, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.USER_LIST.forEach(user -> UserMealsUtil.MEAL_LIST.forEach(
                userMeal -> save(user, userMeal)
        ));
    }



    public UserMeal save(User user,UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        Map<Integer, UserMeal> mealMap = new ConcurrentHashMap<>();

        mealMap.put(userMeal.getId(), userMeal);
        repository.put(user, mealMap);

        return userMeal;
    }

    @Override
    public boolean delete(User user, int id) {
        Map<Integer, UserMeal> userMeals = repository.get(user);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public UserMeal get(User user, int id) {
        Map<Integer, UserMeal> userMeals = repository.get(user);
        return userMeals == null ? null : userMeals.get(id);
    }

    @Override
    public Collection<UserMeal> getAll(User user) {
        Map<Integer, UserMeal> userMeals = repository.get(user);
        return userMeals == null ?
                Collections.emptyList() :
                userMeals.values().stream().sorted(USER_MEAL_COMPARATOR).collect(Collectors.toList());
    }
}
