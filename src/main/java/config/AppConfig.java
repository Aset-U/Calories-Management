package config;
import  ru.javawebinar.topjava.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceImpl;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

/**
 * Created by Asset on 28.04.2016.
 */
@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return null;
    }

    @Bean
    public UserMealRepository userMealRepository(){
        return null;
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserMealService userMealService(){
        return new UserMealServiceImpl();
    }

    @Bean
    public UserMealRestController userMealRestController(){
        return new UserMealRestController();
    }

    @Bean
    public AdminRestController adminUserComtroller(){
        return new AdminRestController();
    }
}
