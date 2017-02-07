package com.sharewalk.dao;

import com.sharewalk.model.Grade;

import java.util.List;

public interface GradeDAO {
    void addNewGrade(Grade grade);

    void updateGrade(Grade grade);

    Grade getGrade(Long userId, Long walkId);

    List<Grade> getAllGrades(Long walkId);
}
