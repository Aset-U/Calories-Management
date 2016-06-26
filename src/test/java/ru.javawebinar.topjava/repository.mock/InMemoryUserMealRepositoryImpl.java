package ru.javawebinar.topjava.repository.mock;

import javafx.print.Collation;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    public static final Comparator<UserMeal> USER_MEAL_COMPARATOR = Comparator.comparing(UserMeal::getDateTime).reversed();

    private Map<Integer, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(um -> save(USER_ID, um));

        save(ADMIN_ID, new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510));
        save(ADMIN_ID, new UserMeal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500));
    }


    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        Assert.notNull(userMeal, "userMeal must not be null");

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
    public Collection<UserMeal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Assert.notNull(startDateTime, "startDateTime must not be null");
        Assert.notNull(endDateTime, "endDateTime must not be null");
        Objects.requireNonNull(endDateTime);
        return getAll(userId).stream()
                .filter(meal -> TimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }
}
