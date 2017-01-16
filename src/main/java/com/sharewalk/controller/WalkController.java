package com.sharewalk.controller;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Comment;
import com.sharewalk.model.Walk;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class WalkController {

    private final WalkService walkService;

    private WalkDAO walkDAO;

    @Autowired
    public WalkController(WalkService walkService, WalkDAO walkDAO) {
        this.walkService = walkService;
        this.walkDAO = walkDAO;
    }

    public WalkController(WalkService walkService) {

        this.walkService = walkService;
    }

    //tu jeszcze z tym dodawaniem nie nie jest zrobione to tego nie testuje
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generate() {
        Random random = new Random();
        Walk walk = new Walk();
        walk.setName("some name" + (random.nextInt() % 1000));
        walkService.addWalk(walk);
        return "home";
    }

    @GetMapping("/walks")
    public ResponseEntity getAllWalks(@RequestParam(value = "starts_with", required = false) String startsWith) {
        if (startsWith == null) {
            return new ResponseEntity(walkDAO.listWalks(), HttpStatus.OK);
        } else {
            return new ResponseEntity(walkDAO.listWalks(startsWith), HttpStatus.OK);
        }
    }

    @GetMapping("/walks/{id}")
    public ResponseEntity getWalk(@PathVariable("id") Long id) {
        List<Walk> walk = walkDAO.getWalk(id);
        if (walk == null) {
            return new ResponseEntity("No Walk found for Walk ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(walk, HttpStatus.OK);
    }

    @GetMapping("/walks/{id}/comments")
    public ResponseEntity listComments(@PathVariable("id") Long id) {
        List<Comment> userWalk = walkDAO.getWalkComments(id);
        if (userWalk == null) {
            return new ResponseEntity("No Comments found for Walk ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(userWalk, HttpStatus.OK);
    }
}


