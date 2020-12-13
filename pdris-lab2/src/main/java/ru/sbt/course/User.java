package ru.sbt.course;

import java.util.Objects;

public class User {
    final private String name;
    final private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
