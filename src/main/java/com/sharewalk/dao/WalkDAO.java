package com.sharewalk.dao;

import com.sharewalk.model.Walk;

import java.util.List;

public interface WalkDAO {
    void addWalk(Walk walk);
    List<Walk> listWalks();
}
