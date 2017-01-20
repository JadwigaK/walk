package com.sharewalk.dao;

import com.sharewalk.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CommentDAOImpl implements CommentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public void addNewComment(Comment comment) {
        entityManager.persist(comment);
    }
}
