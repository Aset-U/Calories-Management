package ru.javawebinar.topjava.repository.mock;

import javafx.print.Collation;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    public static final Comparator<UserMeal> USER_MEAL_COMPARATOR = (um1, um2) -> um2.getDateTime().compareTo(um1.getDateTime());

    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.USER_LIST.forEach(user -> UserMealsUtil.MEAL_LIST.forEach(
                userMeal -> save(user.getId(), userMeal)
        ));
    }


    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        Objects.requireNonNull(userMeal);

        Integer mealId = userMeal.getId();

        if (userMeal.isNew()) {
            mealId = counter.incrementAndGet();
            userMeal.setId(mealId);
        }else if (get(userId, mealId) == null){
            return null;
        }

        Map<Integer, UserMeal> userMeals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        userMeals.put(mealId, userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int userId, int id) {
        Map<Integer, UserMeal> userMeals = repository.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public UserMeal get(int userId, int id) {
        Map<Integer, UserMeal> userMeals = repository.get(userId);
        return userMeals == null ? null : userMeals.get(id);
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        Map<Integer, UserMeal> userMeals = repository.get(userId);
        return userMeals == null ?
                Collections.emptyList() :
                userMeals.values().stream().sorted(USER_MEAL_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public Collection<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return getAll(userId).stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                .collect(Collectors.toList());
    }
}
