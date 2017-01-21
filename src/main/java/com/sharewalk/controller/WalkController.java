package com.sharewalk.controller;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Walk;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity getWalk(@PathVariable("walk_id") long walk_id) {
        List<Walk> walk = walkDAO.getWalk(walk_id);
        if (walk == null) {
            return new ResponseEntity("No Walk found for Walk ID " + walk_id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(walk, HttpStatus.OK);
    }

    // nie ma jeszcze testu
    @PostMapping("/users/{user_id}/walks")
    public ResponseEntity<Walk> addNewWalk(@PathVariable("user_id") long user_id, @RequestBody Walk walk) {
        walk.setUser(walkDAO.getUserByID(user_id).get(0));
        walkDAO.addNewWalk(walk);
        return new ResponseEntity(HttpStatus.OK);
    }

    //to tez nie ma jeszcze  testu
    @PutMapping("/users/{user_id}/walks/{walk_id}")
    public ResponseEntity<Walk> updateWalk(@PathVariable("user_id") long user_id, @PathVariable("walk_id") long walk_id, @RequestBody Walk walk){
        walk.setUser(walkDAO.getUserByID(user_id).get(0));
        walk.setId(walk_id);
        walkDAO.updateWalk(walk);
        return new ResponseEntity(HttpStatus.OK);
    }

}


