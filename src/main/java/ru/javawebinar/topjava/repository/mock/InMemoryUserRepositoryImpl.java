package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.jar.Pack200;
import java.util.stream.Collectors;

/**
 * Created by Asset on 10.03.2016.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.USER_LIST.forEach(this::save);
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        Objects.requireNonNull(user);

        if(user.isNew()){
            user.setId(counter.incrementAndGet());
        }

        return repository.put(user.getId(), user);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        Objects.requireNonNull(email);

        return repository.values().stream().filter(user -> email.equals(user.getEmail())).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return repository.values().stream().sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList());
    }
}
