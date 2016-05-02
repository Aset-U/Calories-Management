package ru.javawebinar.topjava.web;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;


public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);

    private ConfigurableApplicationContext springContex;
    private UserMealRestController mealRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContex = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealRestController  = springContex.getBean(UserMealRestController.class);
    }

    @Override
    public void destroy() {
        springContex.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action == null){
            final UserMeal userMeal = new UserMeal(
                    LocalDateTime.parse(req.getParameter("dateTime")),
                    req.getParameter("description"),
                    Integer.valueOf(req.getParameter("calories")));

            if (req.getParameter("id").isEmpty()) {
                LOG.info("Create {}", userMeal);
                mealRestController.create(userMeal);
            }else {
                LOG.info("Update {}", userMeal);
                mealRestController.update(userMeal, getId(req));
            }
            resp.sendRedirect("meals");
        } else if (action.equals("filter")) {
            LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", req));
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", req));
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", req));
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", req));
            req.setAttribute("mealList", mealRestController.getBetween(startDate, endDate, startTime, endTime));
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            req.setAttribute("mealList", mealRestController.getAll());
            req.getRequestDispatcher("mealList.jsp").forward(req, resp);
        } else if (action.equals("delete")){
            int id = getId(req);
            LOG.info("Delete {}", id);
            mealRestController.delete(id);
            resp.sendRedirect("meals");
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000):        // create
                    mealRestController.get(getId(req));                 // update
            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        }
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }

    private int getId(HttpServletRequest request){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}













