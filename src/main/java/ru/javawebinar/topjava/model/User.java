package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Asset on 08.03.2016.
 */
public class User extends NamedEntity {

    protected String email;

    protected String password;

    protected boolean enabled = true;

    protected Date registered = new Date();

    protected Set<Role> roles;

    protected int caloriesPerDay = UserMealsUtil.DEFAULT_CALORIES_PER_DAY;

    public User() {
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password, true, EnumSet.of(role, roles), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles, int caloriesPerDay) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
        this.caloriesPerDay = caloriesPerDay;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                ", caloriesPerDay=" + caloriesPerDay +
                '}';
    }


}
