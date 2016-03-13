package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;

/**
 * Created by Asset on 10.03.2016.
 */
public class SpringMain {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserComtroller = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserComtroller.create(new User(1, "Batman", "batman@enterprise.com", "123Hyee", Role.ROLE_ADMIN)));
        }
    }
}
