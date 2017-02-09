package com.sharewalk.dao;

import com.sharewalk.model.Comment;

import java.util.List;

public interface CommentDAO {
    void addNewComment(Comment comment);

    List<Comment> listComments(Long walkId);
}
