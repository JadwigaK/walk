package com.sharewalk.controller;

import com.sharewalk.model.Grade;
import com.sharewalk.service.GradeService;
import com.sharewalk.service.UserService;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class GradeController {
    private final GradeService gradeService;

    private final WalkService walkService;

    private final UserService userService;


    @Autowired
    public GradeController(GradeService gradeService, WalkService walkService, UserService userService) {
        this.gradeService = gradeService;
        this.walkService = walkService;
        this.userService = userService;
    }

    @PostMapping("/users/{user_id}/walks/{walk_id}/grades")
    public ResponseEntity<Grade> addGrade(@PathVariable("user_id") Long userId, @PathVariable("walk_id") Long walkId, @RequestBody Grade grade) {
        gradeService.addNewGrade(grade, userId, walkId);
        return new ResponseEntity(grade, HttpStatus.OK);
    }

    @PutMapping("/users/{user_id}/walks/{walk_id}/grades/{grade_id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable("user_id") Long userId, @PathVariable("walk_id") Long walkId, @PathVariable("grade_id") Long gradeId, @RequestBody Grade grade) {
        if (userService.getUserById(userId) == null) {
            return new ResponseEntity("No User found for User ID: " + userId, HttpStatus.NOT_FOUND);
        } else if (walkService.getWalk(walkId) == null) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else {
            gradeService.updateGrade(grade, userId, walkId, gradeId);
            return new ResponseEntity(grade, HttpStatus.OK);
        }
    }

    @GetMapping("/users/{user_id}/walks/{walk_id}/grades")
    public ResponseEntity<Grade> getGrade(@PathVariable("user_id") Long userId, @PathVariable("walk_id") Long walkId) {
        if (walkService.getWalk(walkId) == null) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else if (userService.getUserById(userId) == null) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(gradeService.getGrade(userId, walkId), HttpStatus.OK);
        }
    }

    @GetMapping("/walks/{walk_id}/grades")
    public ResponseEntity<Grade> getAllGrades(@PathVariable("walk_id") Long walkId) {
        if (walkService.getWalk(walkId) == null) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(gradeService.getAllGrades(walkId), HttpStatus.OK);
        }
    }
}
