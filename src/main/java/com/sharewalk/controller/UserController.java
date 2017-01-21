package com.sharewalk.controller;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.model.User;
import com.sharewalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/{user_id}/walks")
    public ResponseEntity getUserWalks( @PathVariable("user_id") long user_id, @RequestParam(value = "starts_with", required = false) String startsWith) {
        if (startsWith == null) {
            return new ResponseEntity(userDAO.listUserWalks(user_id), HttpStatus.OK);
        } else {
            return new ResponseEntity(userDAO.listUserWalks(user_id, startsWith), HttpStatus.OK);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userDAO.addUser(user);
        return new ResponseEntity(HttpStatus.OK);

    }

}
