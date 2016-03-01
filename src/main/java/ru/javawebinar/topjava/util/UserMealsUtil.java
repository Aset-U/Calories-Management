package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    public static final List<UserMeal> MEAL_LIST = Arrays.asList(
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
    );

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static void main(String[] args) {
        List<UserMealWithExceed> filteredMealsWithExceeded = getFilteredMealsWithExceededByStream(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), DEFAULT_CALORIES_PER_DAY);
        filteredMealsWithExceeded.forEach(System.out::println);

        System.out.println(getFilteredMealsWithExceeded(MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), DEFAULT_CALORIES_PER_DAY));
    }

    public static List<UserMealWithExceed> getWithExceeded(Collection<UserMeal> mealList, int caloriesPerDay) {
        return getFilteredMealsWithExceededByStream(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static UserMealWithExceed createWithExceed(UserMeal meal, boolean exceeded) {
        return new UserMealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceededByStream
            (Collection<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, List<UserMeal>> sumByDay  = mealList.stream().collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate()));

        return mealList.stream()
                .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(),startTime,endTime))
                .map(m -> createWithExceed(m, sumByDay.get(m.getDateTime().toLocalDate())
                        .stream().collect(Collectors.summingInt(um -> um.getCalories()))> caloriesPerDay))
                .collect(Collectors.toList());

    }

    public static List<UserMealWithExceed>  getFilteredMealsWithExceeded
            (List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        //1. Мар для собирание милсов по дням
        Map<LocalDate, List<UserMeal>> sumByDay = new TreeMap<>();

        //2. Собрать в этом сете имеющиеся дни в mealList
        Set<LocalDate> days = new LinkedHashSet<>();

        for (UserMeal meal : mealList) {
            days.add(meal.getDateTime().toLocalDate()); // кладу в days дни
        }

        for (LocalDate day : days) {
            List<UserMeal> userMeals = new ArrayList<>(); // 3. промежуточная коллекция UserMeal
            for (UserMeal m : mealList){
                if (day.equals(m.getDateTime().toLocalDate())){
                    userMeals.add(m); // в userMeals кладу отсортированные по дням итемы
                }
            }
            sumByDay.put(day, userMeals); // получаю итем sumByDay от day и userMeals
        }

        //4. Результирующий лист UserMealWithExceed
        List<UserMealWithExceed> mealWithExceeds = new ArrayList<>();

        for (Map.Entry<LocalDate, List<UserMeal>> entry : sumByDay.entrySet()) {

            int amount = 0; // счетчик каллории за день

            for (UserMeal m : entry.getValue()) {
                amount += m.getCalories();
            }

            if (amount >= caloriesPerDay)
            {
                for (UserMeal m : entry.getValue()) {
                    if (TimeUtil.isBetween(m.getDateTime().toLocalTime(),startTime, endTime)){
                        mealWithExceeds.add(
                                new UserMealWithExceed( m.getDateTime(), m.getDescription(), m.getCalories(), true)
                        );
                    }
                }
            }
            else {
                for (UserMeal m : entry.getValue()) {
                    if (TimeUtil.isBetween(m.getDateTime().toLocalTime(),startTime, endTime)){
                        mealWithExceeds.add(
                                new UserMealWithExceed( m.getDateTime(), m.getDescription(), m.getCalories(), false)
                        );
                    }
                }
            }
        }

        return mealWithExceeds;


    }
}
