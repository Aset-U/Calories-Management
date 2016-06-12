package ru.javawebinar.topjava;


import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class UserTestData {

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;

    public static final User USER = new User(USER_ID, "User", "user@mail.ru", "password", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN);

    public static final ModelMatcher<User, TestUser> MATCHER = new ModelMatcher<>(u -> ((u instanceof TestUser) ? (TestUser) u : new TestUser(u)));

    public static class TestUser extends User {

        public TestUser(User u) {
            this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getRoles(), u.getCaloriesPerDay());
        }

        public TestUser(String name, String email, String password, Role role, Role... roles) {
            this(null, name, email, password, true, EnumSet.of(role, roles), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
        }

        public TestUser(Integer id, String name, String email, String password, boolean enabled, Set<Role> roles, int caloriesPerDay) {
            super(id, name, email, password,  enabled, roles, caloriesPerDay);
        }

        public User asUser(){
            return new User(this);
        }

        @Override
        public String toString() {
            return "User (" +
                    "id=" + id +
                    ", email=" + email +
                    ", name=" + name +
                    ", password=" + password +
                    ", enabled=" + enabled +
                    ", authorities=" + roles +
                    ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            TestUser that = (TestUser) o;
            return Objects.equals(this.password, that.password)
                    && Objects.equals(this.id, that.id)
                    && Objects.equals(this.name, that.name)
                    && Objects.equals(this.email, that.email)
                    && Objects.equals(this.caloriesPerDay, that.caloriesPerDay)
                    && Objects.equals(this.enabled, that.enabled);
        }
    }

}
