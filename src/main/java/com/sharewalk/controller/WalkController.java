package com.sharewalk.controller;

import com.sharewalk.model.Walk;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jadwiga on 2017-01-07.
 */
@Controller
public class WalkController {

    private WalkService walkService;

    @Autowired(required = true)
    @Qualifier(value = "walkService")
    public void setWalkService(WalkService ps) {
        this.walkService = ps;
    }

    @RequestMapping(value = "/walks", method = RequestMethod.GET)
    public String listWalks(Model model) {
        model.addAttribute("walk", new Walk());
        model.addAttribute("listWalks", this.walkService.listWalks());
        return "walk";
    }

}

