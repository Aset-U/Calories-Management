package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
        if(user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
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
        for (Map.Entry<Integer, User> pair : repository.entrySet()) {
            User u = pair.getValue();
            if (u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> users = new ArrayList<>();
        repository.forEach((integer, user) -> users.add(user));

        return users.stream().sorted((u1, u2) -> u1.getName().compareTo(u2.getName())).collect(Collectors.toList());
    }
}
