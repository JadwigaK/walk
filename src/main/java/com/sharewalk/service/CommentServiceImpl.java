package com.sharewalk.service;

import com.sharewalk.dao.CommentDAO;
import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final UserDAO userDAO;
    private final WalkDAO walkDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO, UserDAO userDAO, WalkDAO walkDAO) {
        this.userDAO = userDAO;
        this.walkDAO = walkDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    @Transactional
    public void addNewComment(Comment comment, Long userId, Long walkId) {
        comment.setUser(userDAO.getUserById(userId));
        comment.setWalk(walkDAO.getWalk(walkId));
        commentDAO.addNewComment(comment);
    }

    @Override
    public List<Comment> listComments(Long walkId) {
        return commentDAO.listComments(walkId);
    }
}
