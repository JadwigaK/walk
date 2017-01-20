package com.sharewalk.service;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;

import java.util.List;

public interface UserService {
    List<Walk> listUserWalks(long userid);
    List<Walk> listUserWalks(long userid, String nameStartsWith);
    void addUser(User user);
}
