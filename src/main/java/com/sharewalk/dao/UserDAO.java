package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;

import java.util.List;

public interface UserDAO {
    List<Walk> listUserWalks(Long user_id);

    List<Walk> listUserWalks(Long userId, String nameStartsWith);

    User getUserById(Long id);

    void addUser(User user);
}
