package com.sharewalk.controller;

import com.sharewalk.dao.CommentDAO;
import com.sharewalk.model.Comment;
import com.sharewalk.model.Walk;
import com.sharewalk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    private final CommentService commentService;

    private CommentDAO commentDAO;

    @Autowired
    public CommentController(CommentService commentService, CommentDAO commentDAO) {
        this.commentService = commentService;
        this.commentDAO = commentDAO;
    }
    // jeszcze nie dziala tu jeszcze nie ma tetsu
    @PostMapping("/users/{user_id}/walks/{walk_id}/comment")
    public ResponseEntity<Walk> addComment(@PathVariable("user_id") long user_id, @PathVariable("walk_id") long walk_id, @RequestBody Comment comment){
        comment.setUserId(user_id);
        comment.setWalkId(walk_id);
        commentDAO.addNewComment(comment);
        return new ResponseEntity(HttpStatus.OK);
    }
}
