package com.sharewalk.service;

import com.sharewalk.model.Grade;

import java.util.List;

public interface GradeService {
    void addNewGrade(Grade grade, Long userId, Long walkId);

    void updateGrade(Grade grade, Long userId, Long walkId, Long gradeId);

    Grade getGrade(Long userId, Long walkId);

    List<Grade> getAllGrades(Long walkId);
}
