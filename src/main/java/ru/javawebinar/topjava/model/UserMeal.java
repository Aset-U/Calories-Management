package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(name = UserMeal.GET, query = "SELECT um FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT um FROM UserMeal um WHERE um.user.id=:userId ORDER BY um.dateTime DESC"),
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal um WHERE um.id=:id AND um.user.id=:userId"),
        @NamedQuery(name = UserMeal.GET_BETWEEN, query = "SELECT um FROM UserMeal um " +
                                                            "WHERE um.user.id=:userId AND um.dateTime BETWEEN :startDate AND :endDate order by um.dateTime DESC"),
})

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class UserMeal extends BaseEntity{

    public static final String GET = "UserMeal.get";
    public static final String ALL_SORTED = "UserMeal.getAll";
    public static final String DELETE = "UserMeal.delete";
    public static final String GET_BETWEEN = "UserMeal.getBetween";

    @Column(name = "date_time", nullable = false)
    @NotNull
    private  LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    private  String description;

    @Column(name = "calories", nullable = false)
    @Range(min = 10, max = 5000)
    protected   int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User  user;

    public UserMeal(){}

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
