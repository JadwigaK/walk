package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;

import java.util.List;

public interface UserDAO {
    List<Walk> listUserWalks(long user_id);
    List<Walk> listUserWalks(long user_id, String nameStartsWith);
    void addUser(User user);
}
