package com.sharewalk.dao;

import com.sharewalk.model.Comment;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;

import java.util.List;

public interface WalkDAO {
    void addNewWalk(Walk walk);
    List<Walk> listWalks();
    List<Walk> listWalks(String startsWith);
    List<Walk> getWalk(long id);
    //List<Comment> getWalkComments(long id);
    void updateWalk(Walk walk);

}
