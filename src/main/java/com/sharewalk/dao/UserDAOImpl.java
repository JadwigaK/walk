package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Walk> listUserWalks(Long userId) {
        Query query =
                entityManager.createNamedQuery("Walk.findAllForUser").setParameter("userid", userId);
        return query.getResultList();
    }

    @Override
    public List<Walk> listUserWalks(Long userId, String startsWith) {
        Query query =
                entityManager.createNamedQuery("Walk.findAllForUserStartsWith").setParameter("startsWith", startsWith + "%").setParameter("userid", userId);
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        Query query =
                entityManager.createNamedQuery("User.findUserByID").setParameter("id", id);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        Query query =
                entityManager.createNamedQuery("User.findUserByEmail").setParameter("email", email);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
