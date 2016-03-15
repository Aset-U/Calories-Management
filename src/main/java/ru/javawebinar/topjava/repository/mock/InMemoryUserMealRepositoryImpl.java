package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {

    private Map<User, Map<Integer, UserMeal>> repository = new ConcurrentHashMap<>();

    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(this::save);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        Map<Integer, UserMeal> mealMap = new HashMap<>();

        mealMap.put(userMeal.getId(), userMeal);
        repository.put(null, mealMap);

        return userMeal;
    }

    public UserMeal save(User user,UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        Map<Integer, UserMeal> mealMap = new HashMap<>();

        mealMap.put(userMeal.getId(), userMeal);
        repository.put(user, mealMap);

        return userMeal;
    }

    @Override
    public void delete(int id) {
        repository.get(null).remove(id);
    }

    @Override
    public void delete(User user, int id) {
        repository.get(user).remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return repository.get(null).get(id);
    }

    @Override
    public UserMeal get(User user, int id) {
        return repository.get(user).get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return repository.get(null).values();
    }

    @Override
    public Collection<UserMeal> getAll(User user) {
        return repository.get(user).values();
    }
}
