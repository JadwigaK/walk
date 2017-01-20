package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Walk> listUserWalks(long user_id) {
        TypedQuery<Walk> query =
                entityManager.createNamedQuery("Walk.findAllForUser", Walk.class).setParameter("userid", user_id);
        return query.getResultList();
    }

    @Override
    public List<Walk> listUserWalks(long user_id, String startsWith) {
        TypedQuery<Walk> query =
                entityManager.createNamedQuery("Walk.findAllForUserStartsWith", Walk.class).setParameter("startsWith",startsWith+"%").setParameter("userid", user_id);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
