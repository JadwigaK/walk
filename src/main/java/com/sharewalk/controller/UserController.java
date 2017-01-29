package com.sharewalk.controller;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.model.User;
import com.sharewalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
public class UserController {
    private final UserService userService;

    private UserDAO userDAO;

    @Autowired
    public UserController(UserService userService, UserDAO userDAO) {
        this.userService = userService;
        this.userDAO = userDAO;
    }

    @GetMapping("/users/{user_id}/walks")
    public ResponseEntity getUserWalks( @PathVariable("user_id") Long userId, @RequestParam(value = "starts_with", required = false) String startsWith) {
        if (userDAO.listUserWalks(userId).equals(Collections.emptyList())){
            return new ResponseEntity("No User found for User ID: " + userId, HttpStatus.NOT_FOUND);
        } else {
            if (startsWith == null) {
                return new ResponseEntity(userDAO.listUserWalks(userId), HttpStatus.OK);
            } else {
                return new ResponseEntity(userDAO.listUserWalks(userId, startsWith), HttpStatus.OK);
            }
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userDAO.addUser(user);
        return new ResponseEntity(user, HttpStatus.OK);

    }

}
