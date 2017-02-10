package com.sharewalk.service;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;

import java.util.List;

public interface UserService {
    List<Walk> listUserWalks(Long userId);

    List<Walk> listUserWalks(Long userId, String nameStartsWith);

    User getUserById(Long id);

    User getUserByEmail(String email);

    void addUser(User user);
}
