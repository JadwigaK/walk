package com.sharewalk.service;

import com.sharewalk.dao.GradeDAO;
import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeDAO gradeDAO;

    private final UserDAO userDAO;
    private final WalkDAO walkDAO;

    @Autowired
    public GradeServiceImpl(GradeDAO gradeDAO, UserDAO userDAO, WalkDAO walkDAO) {
        this.gradeDAO = gradeDAO;
        this.userDAO = userDAO;
        this.walkDAO = walkDAO;
    }

    @Override
    @Transactional
    public void addNewGrade(Grade grade, Long userId, Long walkId) {
        grade.setUser(userDAO.getUserById(userId));
        grade.setWalk(walkDAO.getWalk(walkId));
        gradeDAO.addNewGrade(grade);
    }

    @Override
    @Transactional
    public void updateGrade(Grade grade, Long userId, Long walkId, Long gradeId) {
        grade.setId(gradeId);
        grade.setUser(userDAO.getUserById(userId));
        grade.setWalk(walkDAO.getWalk(walkId));
        gradeDAO.updateGrade(grade);
    }

    @Override
    @Transactional
    public Grade getGrade(Long userId, Long walkId) {
        return gradeDAO.getGrade(userId, walkId);
    }

    @Override
    @Transactional
    public List<Grade> getAllGrades(Long walkId) {
        return gradeDAO.getAllGrades(walkId);
    }
}
