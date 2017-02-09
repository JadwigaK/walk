package com.sharewalk.dao;

import com.sharewalk.model.Grade;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GradeDAOImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private GradeDAOImpl instance;

    @Mock
    private EntityManager entityManager;

    @Mock
    Query query;

    @Mock
    Query query1;

    @Mock
    Query query2;

    @Mock
    Query query3;

    @Test
    public void addCommentTest() {
        //Given
        Grade grade = new Grade();
        //When
        instance.addNewGrade(grade);
        //Then
        verify(entityManager).persist(grade);
    }

    @Test
    public void updateCommentTest() {
        //Given
        Grade grade = new Grade();
        //When
        instance.updateGrade(grade);
        //Then
        verify(entityManager).merge(grade);
    }

    @Test
    public void getGradeTest() {
        //given
        Long walkId = 1L;
        Long userId = 2L;
        Grade grade1 = new Grade(2.5F, new Walk(), new User());
        when(entityManager.createNamedQuery("Grade.findAllByWalkAndUser")).thenReturn(query1);
        when(query1.setParameter("walkid", walkId)).thenReturn(query2);
        when(query2.setParameter("userid", userId)).thenReturn(query3);
        when(query3.getResultList()).thenReturn(Arrays.asList(grade1));
        //when
        Grade result = instance.getGrade(userId, walkId);
        //then
        assertEquals(grade1, result);
    }

    @Test
    public void getAllGradesTest() {
        //given
        Long walkId = 1L;
        Long userId = 2L;
        Grade grade1 = new Grade(2.5F, new Walk(), new User());
        Grade grade2 = new Grade(2.5F, new Walk(), new User());
        when(entityManager.createNamedQuery("Grade.findAllByWalk")).thenReturn(query1);
        when(query1.setParameter("walkid", walkId)).thenReturn(query2);
        when(query2.getResultList()).thenReturn(Arrays.asList(grade1, grade2));
        //when
        List<Grade> list = instance.getAllGrades(walkId);
        //then
        assertEquals(2, list.size());
        assertEquals(grade1, list.get(0));
        assertEquals(grade2, list.get(1));
    }
}
