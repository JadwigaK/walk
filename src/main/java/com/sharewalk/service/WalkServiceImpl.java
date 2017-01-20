package com.sharewalk.service;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Comment;
import com.sharewalk.model.Walk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WalkServiceImpl implements WalkService {

    private final WalkDAO walkDAO;

    @Autowired
    public WalkServiceImpl(WalkDAO walkDAO) {
        this.walkDAO = walkDAO;
    }

    @Override
    @Transactional
    public List<Walk> listWalks() {
        return this.walkDAO.listWalks();
    }

    @Override
    @Transactional
    public List<Walk> getWalk(long id) {
        return this.walkDAO.getWalk(id);
    }

//    @Override
//    public List<Comment> getWalkComments(long id) {
//        return this.walkDAO.getWalkComments(id);
//    }

    @Override
    @Transactional
    public void addNewWalk(Walk walk) {
        walkDAO.addNewWalk(walk);
    }

    @Override
    @Transactional
    public void updateWalk(Walk walk) {
        walkDAO.updateWalk(walk);
    }

    @Override
    @Transactional
    public List<Walk> listWalks(String startsWith) {
        return this.walkDAO.listWalks(startsWith);
    }
}
