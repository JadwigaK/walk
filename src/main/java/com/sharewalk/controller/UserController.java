package com.sharewalk.controller;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Comment;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import com.sharewalk.service.UserService;
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
public class UserController {
    private final UserService userService;

    private UserDAO userDAO;

    @Autowired
    public UserController(UserService userService, UserDAO userDAO) {
        this.userService = userService;
        this.userDAO = userDAO;
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}/walks")
    public ResponseEntity getUserWalks(@RequestParam(value = "starts_with", required = false) String startsWith, @PathVariable("id") long userid) {
        if (startsWith == null) {
            return new ResponseEntity(userDAO.listUserWalks(userid), HttpStatus.OK);
        } else {
            return new ResponseEntity(userDAO.listUserWalks(userid, startsWith), HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userDAO.addUser(user);
        return new ResponseEntity(HttpStatus.OK);

    }

}
