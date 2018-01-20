package com.company.other;

import com.company.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private static Repository instance = new Repository();

    private List<User> users;

    public static Repository getInstance() {
        return instance;
    }

    private Repository() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<String> getUserLogins() {
        return users.stream()
                .map(User::getLogin)
                .collect(Collectors.toList());
    }
}
