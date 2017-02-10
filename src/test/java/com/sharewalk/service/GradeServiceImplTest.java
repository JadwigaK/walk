package com.sharewalk.service;

import com.sharewalk.dao.GradeDAO;
import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Grade;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GradeServiceImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private GradeServiceImpl instance;

    @Mock
    private GradeDAO gradeDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private WalkDAO walkDAO;

    @Test
    public void addNewGradeTest() {
        //given
        Grade mockGrade = mock(Grade.class);
        Long userId = 1L;
        Long walkId = 2L;
        //when
        instance.addNewGrade(mockGrade, userId, walkId);
        //then
        verify(gradeDAO).addNewGrade(mockGrade);
    }

    @Test
    public void updateGradeTest() {
        //given
        Grade mockGrade = mock(Grade.class);
        Long userId = 1L;
        Long walkId = 2L;
        Long gradeId = 2L;
        //when
        instance.updateGrade(mockGrade, userId, walkId, gradeId);
        //then
        verify(gradeDAO).updateGrade(mockGrade);
    }

    @Test
    public void getGradeTest() {
        //given
        Long userId = 1L;
        Long walkId = 2L;
        //when
        instance.getGrade(userId, walkId);
        //then
        verify(gradeDAO).getGrade(userId, walkId);
    }

    @Test
    public void getAllGradesTest() {
        //given
        Long walkId = 2L;
        //when
        instance.getAllGrades(walkId);
        //then
        verify(gradeDAO).getAllGrades(walkId);
    }


}
