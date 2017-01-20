package com.sharewalk.service;

import com.sharewalk.dao.CommentDAO;
import com.sharewalk.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentServiceImpl implements  CommentService {
    private final CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    @Transactional
    public void addNewComment(Comment comment) {
        commentDAO.addNewComment(comment);
    }
}
