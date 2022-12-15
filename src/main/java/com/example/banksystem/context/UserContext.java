package com.example.banksystem.context;

import com.example.banksystem.model.User;

public class UserContext {

    private static final UserContext context = new UserContext();

    public static UserContext getInstance() {
        return context;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}