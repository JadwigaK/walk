package com.sharewalk.controller;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Walk;
import com.sharewalk.service.UserService;
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
    private final UserService userService;

    @Autowired
    public WalkController(WalkService walkService, UserService userService) {
        this.walkService = walkService;
        this.userService = userService;
    }

    @GetMapping("/walks")
    public ResponseEntity getAllWalks(@RequestParam(value = "starts_with", required = false) String startsWith) {
        if (startsWith == null) {
            return new ResponseEntity(walkService.listWalks(), HttpStatus.OK);
        } else {
            return new ResponseEntity(walkService.listWalks(startsWith), HttpStatus.OK);
        }
    }

    @GetMapping("/walks/{walk_id}")
    public ResponseEntity getWalk(@PathVariable("walk_id") Long walkId) {
        Walk walk = walkService.getWalk(walkId);
        if (walk == null) {
            return new ResponseEntity("No Walk found for Walk ID " + walkId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(walkService.getWalk(walkId), HttpStatus.OK);
    }

    @PostMapping("/users/{user_id}/walks")
    public ResponseEntity<Walk> addNewWalk(@PathVariable("user_id") Long userId, @RequestBody Walk walk) {
        if (userService.getUserById(userId)==null){
            return new ResponseEntity("No User found for User ID: " + userId, HttpStatus.NOT_FOUND);
        } else {
            walkService.addNewWalk(walk, userId);
            return new ResponseEntity(walk, HttpStatus.OK);
        }
    }

    @PutMapping("/users/{user_id}/walks/{walk_id}")
    public ResponseEntity<Walk> updateWalk(@PathVariable("user_id") Long userId, @PathVariable("walk_id") Long walkId, @RequestBody Walk walk){
        if (userService.getUserById(userId)==null) {
            return new ResponseEntity("No User found for User ID: " + userId, HttpStatus.NOT_FOUND);
        }else if (walkService.getWalk(walkId)==null) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else {
            walkService.updateWalk(walk, userId, walkId);
            return new ResponseEntity(walk, HttpStatus.OK);
        }
    }

}


