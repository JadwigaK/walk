package com.sharewalk.service;

import com.sharewalk.model.Comment;
import com.sharewalk.model.Walk;

import java.util.List;

public interface WalkService {
    void addWalk(Walk walk);
    List<Walk> listWalks();
    List<Walk> listWalks(String startsWith);
    List<Walk> getWalk(Long id);
    List<Comment> getWalkComments(Long id);
}
