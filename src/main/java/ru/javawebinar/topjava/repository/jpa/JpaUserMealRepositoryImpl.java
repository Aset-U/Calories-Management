package ru.javawebinar.topjava.repository.jpa;


import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional(readOnly = true  )
public class JpaUserMealRepositoryImpl implements UserMealRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        User ref = em.getReference(User.class, userId);
        userMeal.setUser(ref);

        if (userMeal.isNew()) {
            em.persist(userMeal);
            return userMeal;
        }
        else {
            return get(userId, userMeal.getId()) == null ? null : em.merge(userMeal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int userId, int id) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int userId, int id) {
        List<UserMeal> userMeals = em.createNamedQuery(UserMeal.GET, UserMeal.class)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(userMeals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.GET_BETWEEN, UserMeal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
