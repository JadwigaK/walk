package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class WalkDAOImpl implements WalkDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Walk> listWalks() {
        Query query =
                entityManager.createNamedQuery("Walk.findAll");
        return query.getResultList();
    }

    @Override
    public List<Walk> listWalks(String startsWith) {
        Query query =
                entityManager.createNamedQuery("Walk.findAllStartsWith").setParameter("startsWith",startsWith+"%");
        return query.getResultList();
    }

    @Override
    public List<Walk> getWalk(long id) {
        Query query =
                entityManager.createNamedQuery("Walk.findByID").setParameter("id",id);
        return query.getResultList();
    }

    @Override
    public List<User> getUserByID(long id) {
        Query query =
                entityManager.createNamedQuery("User.findUserByID").setParameter("id",id);
        return query.getResultList();
    }

    //jeszcze nie testuje
    @Override
    @Transactional
    public void updateWalk(Walk walk) {
        entityManager.merge(walk);
    }

    //add jeszcze nie testuje
    @Override
    @Transactional
    public void addNewWalk(Walk walk) {
        //mialam tu persist() ale nie dodawało mi nowych walków dla danego usera
        entityManager.merge(walk);
    }
}
