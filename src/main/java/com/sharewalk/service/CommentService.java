package com.sharewalk.service;

import com.sharewalk.model.Comment;

import java.util.List;

public interface CommentService {
    void addNewComment(Comment comment, Long userId, Long walkId);

    List<Comment> listComments(Long walkId);
}
