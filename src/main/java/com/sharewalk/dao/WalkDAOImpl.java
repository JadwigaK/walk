package com.sharewalk.dao;

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
    public void addWalk(Walk walk) {
        entityManager.persist(walk);
    }
}
