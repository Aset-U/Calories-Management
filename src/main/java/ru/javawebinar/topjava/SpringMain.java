package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.config.AppConfig;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.to.UserMealWithExceed;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Asset on 10.03.2016.
 */
public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class)) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserComtroller = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserComtroller.create(new User(1, "Batman", "batman@enterprise.com", "123Hyee", Role.ROLE_ADMIN)));
            System.out.println();

            UserMealRestController mealRestController = appCtx.getBean(UserMealRestController.class);

            List<UserMealWithExceed> filteredMealsWithExceeded =
                    mealRestController.getBetween(
                            LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 31),
                            LocalTime.of(7, 0), LocalTime.of(11, 0));

            filteredMealsWithExceeded.forEach(System.out::println);
        }
    }
}
