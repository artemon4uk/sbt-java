package ru.sbt.course.registration;

import org.springframework.stereotype.Service;
import ru.sbt.course.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class DataBase {
    private final Map<String, User> users = new HashMap<>();

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public boolean doesUserExist(User user) {
        return users.containsKey(user.getName());
    }

    public boolean doesPasswordRight(User user) {
        if (!doesUserExist(user)) {
            return false;
        }
        return users.get(user.getName()).getPassword().equals(user.getPassword());
    }
}
