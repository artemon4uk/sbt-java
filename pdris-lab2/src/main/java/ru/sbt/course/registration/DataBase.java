package ru.sbt.course.registration;

import ru.sbt.course.User;

import java.util.HashMap;
import java.util.Map;


public class DataBase {
    private final static Map<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.getName(), user);
    }

    public static boolean doesUserExist(User user) {
        return users.containsKey(user.getName());
    }

    public static boolean doesPasswordRight(User user) {
        if (!doesUserExist(user)) {
            return false;
        }
        return users.get(user.getName()).getPassword().equals(user.getPassword());
    }
}
