package com.sharewalk.controller;


import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import com.sharewalk.service.UserService;
import com.sharewalk.service.WalkService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class WalkControllerTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private WalkController instance;

    @Mock
    private WalkService walkService;

    @Mock
    private UserService userService;

    @Test
    public void getAllWalksGeneralTest() {
        //given
        List<Walk> walks = new ArrayList<Walk>();
        when(walkService.listWalks()).thenReturn(walks);
        //when
        ResponseEntity responseEntity = instance.getAllWalks(null);
        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walks, (List) responseEntity.getBody());
    }


    @Test
    public void getAllWalksStartsWithTestOK() {
        //given
        List<Walk> walks = new ArrayList<Walk>();
        when(walkService.listWalks("walk 1")).thenReturn(walks);
        //when
        ResponseEntity responseEntity = instance.getAllWalks("walk 1");
        //then
        verify(walkService).listWalks("walk 1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walks, (List) responseEntity.getBody());
    }

    @Test
    public void getWalkByIDTestOK() {
        //given
        Long id = 1L;
        Walk walk = new Walk();
        when(walkService.getWalk(id)).thenReturn(walk);
        //when
        ResponseEntity responseEntity = instance.getWalk(id);
        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walk, (Walk) responseEntity.getBody());
    }

    @Test
    public void getWalkByIDTestNotFound() {
        //given
        Long id = 2L;
        when(walkService.getWalk(id)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.getWalk(id);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void addNewWalkOK() {
        //given
        Long id = 1L;
        Walk walk = new Walk();
        User mockUser = mock(User.class);
        when(userService.getUserById(id)).thenReturn(mockUser);
        Whitebox.setInternalState(walk, "user", mockUser);
        //when
        ResponseEntity responseEntity = instance.addNewWalk(id, walk);
        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walk, (Walk) (responseEntity.getBody()));
    }

    @Test
    public void addNewWalkUserNotFound() {
        //given
        Long id = 2L;
        when(userService.getUserById(id)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.addNewWalk(id, null);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateWalkOK() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Walk walk = new Walk();
        User mockUser = mock(User.class);
        Walk mockWalk = mock(Walk.class);
        when(userService.getUserById(userId)).thenReturn(mockUser);
        when(walkService.getWalk(walkId)).thenReturn(mockWalk);
        //when
        ResponseEntity responseEntity = instance.updateWalk(userId, walkId, walk);
        //then
        verify(walkService).updateWalk(walk, userId, walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walk, (Walk) (responseEntity.getBody()));
    }

    @Test
    public void updateWalkUserNotFound() {
        //given
        Long userId = 2L;
        Long walkId = 0L;
        when(userService.getUserById(userId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.updateWalk(userId, walkId, null);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateWalkWalkNotFound() {
        //given
        Long walkId = 2L;
        Long userId = 0L;
        when(walkService.getWalk(walkId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.updateWalk(userId, walkId, null);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}