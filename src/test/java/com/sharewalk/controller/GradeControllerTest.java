package com.sharewalk.controller;

import com.sharewalk.model.Grade;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import com.sharewalk.service.GradeService;
import com.sharewalk.service.UserService;
import com.sharewalk.service.WalkService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GradeControllerTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private GradeController instance;

    @Mock
    private GradeService gradeService;

    @Mock
    private WalkService walkService;

    @Mock
    private UserService userService;

    @Test
    public void addGradeTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Grade mockGrade = mock(Grade.class);
        //when
        ResponseEntity responseEntity = instance.addGrade(userId, walkId, mockGrade);
        //then
        verify(gradeService).addNewGrade(mockGrade, userId, walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateGradeUserNotFoundTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Long gradeId = 1L;
        Grade mockGrade = mock(Grade.class);
        when(userService.getUserById(userId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.updateGrade(userId, walkId, gradeId, mockGrade);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateGradeWalkNotFoundTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Long gradeId = 1L;
        Grade mockGrade = mock(Grade.class);
        when(walkService.getWalk(walkId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.updateGrade(userId, walkId, gradeId, mockGrade);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateGradeOKTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Long gradeId = 1L;
        Grade mockGrade = mock(Grade.class);
        Walk mockWalk = mock(Walk.class);
        User mockUser = mock(User.class);
        when(walkService.getWalk(walkId)).thenReturn(mockWalk);
        when(userService.getUserById(userId)).thenReturn(mockUser);
        //when
        ResponseEntity responseEntity = instance.updateGrade(userId, walkId, gradeId, mockGrade);
        //then
        verify(gradeService).updateGrade(mockGrade, userId, walkId, gradeId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getGradeUserNotFoundTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        when(userService.getUserById(userId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.getGrade(userId, walkId);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getGradeWalkNotFoundTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        when(walkService.getWalk(walkId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.getGrade(userId, walkId);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getGradeOKTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Walk mockWalk = mock(Walk.class);
        User mockUser = mock(User.class);
        when(walkService.getWalk(walkId)).thenReturn(mockWalk);
        when(userService.getUserById(userId)).thenReturn(mockUser);
        //when
        ResponseEntity responseEntity = instance.getGrade(userId, walkId);
        //then
        verify(gradeService).getGrade(userId, walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAllGradesWalkNotFoundTest() {
        //given
        Long walkId = 1L;
        when(walkService.getWalk(walkId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.getAllGrades(walkId);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getAllGradesOKTest() {
        //given
        Long walkId = 1L;
        Walk mockWalk = mock(Walk.class);
        when(walkService.getWalk(walkId)).thenReturn(mockWalk);
        //when
        ResponseEntity responseEntity = instance.getAllGrades(walkId);
        //then
        verify(gradeService).getAllGrades(walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
