package com.sharewalk.service;

import com.sharewalk.model.Walk;

import java.util.List;

public interface WalkService {
    void addWalk(Walk walk);
    List<Walk> listWalks();
}
