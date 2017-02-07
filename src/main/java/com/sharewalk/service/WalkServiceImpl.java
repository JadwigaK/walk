package com.sharewalk.service;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Walk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalkServiceImpl implements WalkService {

    private final WalkDAO walkDAO;
    private final UserDAO userDAO;

    @Autowired
    public WalkServiceImpl(WalkDAO walkDAO, UserDAO userDAO) {
        this.walkDAO = walkDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<Walk> listWalks() {
        return this.walkDAO.listWalks();
    }

    @Override
    @Transactional
    public Walk getWalk(Long id) {
        return this.walkDAO.getWalk(id);
    }

    @Override
    @Transactional
    public void addNewWalk(Walk walk, Long userId) {
        walk.setUser(userDAO.getUserById(userId));
        walkDAO.addNewWalk(walk);
    }

    @Override
    @Transactional
    public void updateWalk(Walk walk, Long userId, Long walkId) {
        walk.setUser(userDAO.getUserById(userId));
        walk.setId(walkId);
        walkDAO.updateWalk(walk);
    }

    @Override
    @Transactional
    public List<Walk> listWalks(String startsWith) {
        return this.walkDAO.listWalks(startsWith);
    }
}
