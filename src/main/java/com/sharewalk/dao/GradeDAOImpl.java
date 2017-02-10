package com.sharewalk.dao;

import com.sharewalk.model.Grade;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GradeDAOImpl implements GradeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public void addNewGrade(Grade grade) {
        entityManager.persist(grade);
    }

    @Override
    public void updateGrade(Grade grade) {
        entityManager.merge(grade);

    }

    @Override
    public Grade getGrade(Long userId, Long walkId) {
        Query query =
                entityManager.createNamedQuery("Grade.findAllByWalkAndUser").setParameter("walkid", walkId).setParameter("userid", userId);
        return (Grade) query.getResultList().get(0);
    }

    @Override
    public List<Grade> getAllGrades(Long walkId) {
        Query query =
                entityManager.createNamedQuery("Grade.findAllByWalk").setParameter("walkid", walkId);
        return query.getResultList();
    }
}
