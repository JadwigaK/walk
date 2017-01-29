package com.sharewalk.controller;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Walk;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class WalkController {

    private final WalkService walkService;

    private WalkDAO walkDAO;

    @Autowired
    public WalkController(WalkService walkService, WalkDAO walkDAO) {
        this.walkService = walkService;
        this.walkDAO = walkDAO;
    }

    @GetMapping("/walks")
    public ResponseEntity getAllWalks(@RequestParam(value = "starts_with", required = false) String startsWith) {
        if (startsWith == null) {
            return new ResponseEntity(walkDAO.listWalks(), HttpStatus.OK);
        } else {
            return new ResponseEntity(walkDAO.listWalks(startsWith), HttpStatus.OK);
        }
    }

    @GetMapping("/walks/{walk_id}")
    public ResponseEntity getWalk(@PathVariable("walk_id") long walkId) {
        List<Walk> walk = walkDAO.getWalk(walkId);
        if (walk.equals(Collections.emptyList())) {
            return new ResponseEntity("No Walk found for Walk ID " + walkId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(walk, HttpStatus.OK);
    }

    @PostMapping("/users/{user_id}/walks")
    public ResponseEntity<Walk> addNewWalk(@PathVariable("user_id") long userId, @RequestBody Walk walk) {
        if (walkDAO.getUserByID(userId).equals(Collections.emptyList())){
            return new ResponseEntity("No User found for User ID: " + userId, HttpStatus.NOT_FOUND);
        } else {
            walk.setUser(walkDAO.getUserByID(userId).get(0));
            walkDAO.addNewWalk(walk);
            return new ResponseEntity(walk, HttpStatus.OK);
        }
    }

    @PutMapping("/users/{user_id}/walks/{walk_id}")
    public ResponseEntity<Walk> updateWalk(@PathVariable("user_id") long userId, @PathVariable("walk_id") long walkId, @RequestBody Walk walk){
        if (walkDAO.getUserByID(userId).equals(Collections.emptyList())) {
            return new ResponseEntity("No User found for User ID: " + userId, HttpStatus.NOT_FOUND);
        }else if (walkDAO.getWalk(walkId).equals(Collections.emptyList())) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else {
            walk.setUser(walkDAO.getUserByID(userId).get(0));
            walk.setId(walkId);
            walkDAO.updateWalk(walk);
            return new ResponseEntity(walk, HttpStatus.OK);
        }
    }

}


