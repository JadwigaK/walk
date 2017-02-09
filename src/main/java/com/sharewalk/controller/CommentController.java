package com.sharewalk.controller;

import com.sharewalk.model.Comment;
import com.sharewalk.service.CommentService;
import com.sharewalk.service.WalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    private final CommentService commentService;

    private final WalkService walkService;

    @Autowired
    public CommentController(CommentService commentService, WalkService walkService) {
        this.commentService = commentService;
        this.walkService = walkService;
    }

    @PostMapping("/users/{user_id}/walks/{walk_id}/comment")
    public ResponseEntity<Comment> addComment(@PathVariable("user_id") Long userId, @PathVariable("walk_id") Long walkId, @RequestBody Comment comment){
        commentService.addNewComment(comment, userId, walkId);
        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @GetMapping("/walks/{walk_id}/comment")
    public ResponseEntity<Comment> listComments(@PathVariable("walk_id") Long walkId) {
        if (walkService.getWalk(walkId) == null) {
            return new ResponseEntity("No Walk found for Walk ID: " + walkId, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(commentService.listComments(walkId),HttpStatus.OK);
        }
    }
}
