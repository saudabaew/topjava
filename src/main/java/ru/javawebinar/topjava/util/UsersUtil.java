package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static ru.javawebinar.topjava.model.Role.ROLE_ADMIN;
import static ru.javawebinar.topjava.model.Role.ROLE_USER;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1, "userOne", "userOne@mail.ru", "passwordOne", 2000, true, new HashSet<Role>(Arrays.asList(ROLE_USER))),
            new User(2, "userTwo", "userTwo@gmail.com", "passwordTwo", 1000, true, new HashSet<Role>(Arrays.asList(ROLE_USER, ROLE_ADMIN)))
    );
}
