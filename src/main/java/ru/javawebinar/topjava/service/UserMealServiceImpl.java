package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.repository.UserMealRepository;

/**
 * Created by Asset on 10.03.2016.
 */
@Repository
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;
}
