package com.sharewalk.service;

import com.sharewalk.model.Walk;

import java.util.List;

public interface WalkService {

    List<Walk> listWalks();

    List<Walk> listWalks(String startsWith);

    Walk getWalk(Long id);

    void addNewWalk(Walk walk, Long userId);

    void updateWalk(Walk walk, Long userId, Long walkId);
}
