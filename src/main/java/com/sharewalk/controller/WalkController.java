package com.sharewalk.controller;

import com.sharewalk.model.Walk;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

@Controller
public class WalkController {

    private final WalkService walkService;

    @Autowired
    public WalkController(WalkService walkService) {
        this.walkService = walkService;
    }

    @RequestMapping(value = "/walks", method = RequestMethod.GET)
    public String listWalks(Model model) {
        model.addAttribute("listWalks", this.walkService.listWalks());
        return "walk";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String generate(Model model) {
        Random random = new Random();
        Walk walk = new Walk();
        walk.setName("some name" + (random.nextInt() % 1000));
        walkService.addWalk(walk);
        return "home";
    }

}

