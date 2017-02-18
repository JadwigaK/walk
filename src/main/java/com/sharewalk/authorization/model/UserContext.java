package com.sharewalk.authorization.model;

import org.apache.commons.lang3.StringUtils;

public class UserContext {
    private final String username;

    private UserContext(String username) {
        this.username = username;
    }

    public static UserContext create(String username) {
        if (StringUtils.isBlank(username)) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username);
    }

    public String getUsername() {
        return username;
    }
}