package com.sharewalk.service;

import com.sharewalk.dao.WalkDAO;
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
    public void addWalk(Walk walk) {
        walkDAO.addWalk(walk);
    }
}
