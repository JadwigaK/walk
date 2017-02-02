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
    public List<Walk> listUserWalks(long userId) {
        return this.userDAO.listUserWalks(userId);
    }

    @Override
    @Transactional
    public List<Walk> listUserWalks(long userId, String nameStartsWith) {
        return this.userDAO.listUserWalks(userId, nameStartsWith);
    }

    @Override
    public User getUserById(Long id) {
        return this.userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }
}
