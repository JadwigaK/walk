package com.sharewalk.service;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<Walk> listUserWalks(long user_id) {
        return this.userDAO.listUserWalks(user_id);
    }

    @Override
    @Transactional
    public List<Walk> listUserWalks(long user_id, String nameStartsWith) {
        return this.userDAO.listUserWalks(user_id, nameStartsWith);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }
}
