package com.sharewalk.controller;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import com.sharewalk.model.WayPoint;
import com.sharewalk.service.UserService;
import com.sharewalk.service.WalkService;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class WalkControllerTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private WalkController instance;

    @Mock
    private WalkDAO walkDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private WalkService walkService;

    @Mock
    private UserService userService;

    @Test
    public void getAllWalksGeneralTest(){
        Walk walk1 = new Walk("walk 1", new User(), Arrays.asList(new WayPoint()));
        Walk walk2 = new Walk("walk 2", new User(), Arrays.asList(new WayPoint()));
        when(walkService.listWalks()).thenReturn(Arrays.asList(walk1, walk2));
        ResponseEntity responseEntity = instance.getAllWalks(null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, ((List)responseEntity.getBody()).size());
        assertEquals(walk1, ((List)responseEntity.getBody()).get(0));
        assertEquals(walk2, ((List)responseEntity.getBody()).get(1));
    }

    @Test
    public void getAllWalksStartsWithTestOK(){
        List walks = new ArrayList<>();
        when(walkService.listWalks("walk 1")).thenReturn(walks);
        ResponseEntity responseEntity = instance.getAllWalks("walk 1");
        verify(walkService).listWalks("walk 1");
        List result = ((List)responseEntity.getBody());
        assertEquals(walks,result);
    }

    @Test
    public void getWalkByIDTestOK(){
        Long id= Long.valueOf(1L);
        when(walkDAO.getWalk(id)).thenReturn(new Walk("walk 1", null, null));
        ResponseEntity responseEntity = instance.getWalk(id);
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode());
        Walk actualWalk = (Walk)responseEntity.getBody();
        assertEquals(0, actualWalk.getId());
        assertEquals("walk 1", actualWalk.getName());
    }

    @Test
    public void getWalkByIDTestNotFound(){
        Long id= Long.valueOf(2L);
        when(walkDAO.getWalk(id)).thenReturn(null);
        ResponseEntity responseEntity = instance.getWalk(id);
        assertEquals( HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void addNewWalkOK(){
        Long id= Long.valueOf(1L);
        User user = new User();
        user.setId(id);
        when(userDAO.getUserById(id)).thenReturn(user);
        ResponseEntity responseEntity = instance.addNewWalk(id, new Walk("walk 1", user, null));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Walk actualWalk = (Walk)(responseEntity.getBody());
        assertEquals(0, actualWalk.getId());
        assertEquals("walk 1", actualWalk.getName());
        assertEquals(1, actualWalk.getUser().getId());
    }

    @Test
    public void addNewWalkUserNotFound(){
        Long id= Long.valueOf(2L);
        when(userDAO.getUserById(id)).thenReturn(null);
        ResponseEntity responseEntity = instance.addNewWalk(id, null);
        assertEquals( HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateWalkOK(){
        Long userId= Long.valueOf(1L);
        Long walkId= Long.valueOf(1L);
        User user = new User();
        user.setId(userId);
        Walk walk = new Walk("walk 1", user, null);
        walk.setId(walkId);
        Walk updatedWalk = new Walk("walk 1 updated", null, null);
        Walk spyWalk = spy(updatedWalk);
        when(userDAO.getUserById(userId)).thenReturn(user);
        when(walkDAO.getWalk(walkId)).thenReturn(walk);
        ResponseEntity responseEntity = instance.updateWalk(userId, walkId, spyWalk);
        verify(spyWalk).setUser(userDAO.getUserById(userId));
        verify(spyWalk).setId(walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Walk actualWalk = (Walk)(responseEntity.getBody());
        assertEquals(1, actualWalk.getId());
        assertEquals("walk 1 updated", actualWalk.getName());
        assertEquals(1, actualWalk.getUser().getId());
    }

    @Test
    public void updateWalkUserNotFound(){
        Long userId= Long.valueOf(2L);
        when(userService.getUserById(userId)).thenReturn(null);
        ResponseEntity responseEntity = instance.updateWalk(userId, 0L, null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateWalkWalkNotFound(){
        Long walkId= Long.valueOf(2L);
        when(walkService.getWalk(walkId)).thenReturn(null);
        ResponseEntity responseEntity = instance.updateWalk(0L, walkId, null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}