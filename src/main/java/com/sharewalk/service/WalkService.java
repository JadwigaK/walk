package com.sharewalk.service;

import com.sharewalk.model.Walk;

import java.util.List;

public interface WalkService {

    List<Walk> listWalks();
    List<Walk> listWalks(String startsWith);
    List<Walk> getWalk(long id);
    void addNewWalk(Walk walk);
    void updateWalk(Walk walk);
}
