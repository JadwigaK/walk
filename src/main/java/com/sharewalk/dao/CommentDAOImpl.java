package com.sharewalk.dao;

import com.sharewalk.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public void addNewComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public List<Comment> listComments(Long walkId) {
        Query query =
                entityManager.createNamedQuery("Comment.findByWalkID").setParameter("walkid", walkId);
        return query.getResultList();
    }
}
