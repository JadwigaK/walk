package com.sharewalk.service;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Walk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jadwiga on 2017-01-07.
 */

@Service
public class WalkServiceImpl implements WalkService {

    @Autowired
    private WalkDAO walkDAO;

    public void setWalkDAO(WalkDAO walkDAO) {
        this.walkDAO = walkDAO;
    }

    @Override
    @Transactional
    public List<Walk> listWalks() {
        return this.walkDAO.listWalks();
    }
}
