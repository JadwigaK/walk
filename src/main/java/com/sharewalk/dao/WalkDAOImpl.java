package com.sharewalk.dao;

import com.sharewalk.model.Comment;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WalkDAOImpl implements WalkDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Walk> listWalks() {
        TypedQuery<Walk> query =
                entityManager.createNamedQuery("Walk.findAll", Walk.class);
        return query.getResultList();
    }

    @Override
    public List<Walk> listWalks(String startsWith) {
        TypedQuery<Walk> query =
                entityManager.createNamedQuery("Walk.findAllStartsWith", Walk.class).setParameter("startsWith",startsWith+"%");
        return query.getResultList();
    }

    @Override
    public List<Walk> getWalk(Long id) {
        TypedQuery<Walk> query =
                entityManager.createNamedQuery("Walk.findByID", Walk.class).setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public List<Comment> getWalkComments(Long id) {
        TypedQuery<Comment> query =
                entityManager.createNamedQuery("Comment.findByIDComments", Comment.class).setParameter("walkid",id);
        return query.getResultList();
    }

    //add jeszcze nie testuje
    @Override
    public void addWalk(Walk walk) {
        entityManager.persist(walk);
    }
}
