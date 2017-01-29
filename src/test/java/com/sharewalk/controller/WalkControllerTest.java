package com.sharewalk.controller;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
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

    @Test
    public void getAllWalksGeneralTest(){
        when(walkDAO.listWalks()).thenReturn(Arrays.asList(new Walk("walk 1", null, null), new Walk("walk 2", null, null) ));
        ResponseEntity responseEntity = instance.getAllWalks(null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, ((List)responseEntity.getBody()).size());
        assertEquals(Walk.class, ((List)responseEntity.getBody()).get(0).getClass());
    }

    @Test
    public void getAllWalksStartsWithTestOK(){
        when(walkDAO.listWalks("walk 1")).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));
        ResponseEntity responseEntity = instance.getAllWalks("walk 1");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(1, ((List)responseEntity.getBody()).size());
        Walk actualWalk = (Walk)((List)responseEntity.getBody()).get(0);
        assertEquals(0, actualWalk.getId());
        assertEquals("walk 1", actualWalk.getName());
    }

    @Test
    public void getWalkByIDTestOK(){
        Long id= Long.valueOf(1L);
        when(walkDAO.getWalk(id)).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));
        ResponseEntity responseEntity = instance.getWalk(id);
        assertEquals( HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((List)responseEntity.getBody()).size());
        Walk actualWalk = (Walk)((List)responseEntity.getBody()).get(0);
        assertEquals(0, actualWalk.getId());
        assertEquals("walk 1", actualWalk.getName());
    }

    @Test
    public void getWalkByIDTestNotFound(){
        Long id= Long.valueOf(2L);
        when(walkDAO.getWalk(id)).thenReturn(Collections.emptyList());
        ResponseEntity responseEntity = instance.getWalk(id);
        assertEquals( HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void addNewWalkOK(){
        Long id= Long.valueOf(1L);
        User user = new User();
        user.setId(id);
        when(walkDAO.getUserByID(id)).thenReturn(Arrays.asList(user));
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
        when(walkDAO.getUserByID(id)).thenReturn(Collections.emptyList());
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
        when(walkDAO.getUserByID(userId)).thenReturn(Arrays.asList(user));
        when(walkDAO.getWalk(walkId)).thenReturn(Arrays.asList(walk));
        ResponseEntity responseEntity = instance.updateWalk(userId, walkId, spyWalk);
        verify(spyWalk).setUser(walkDAO.getUserByID(userId).get(0));
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
        when(walkDAO.getUserByID(userId)).thenReturn(Collections.emptyList());
        ResponseEntity responseEntity = instance.updateWalk(userId, 0, null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void updateWalkWalkNotFound(){
        Long walkId= Long.valueOf(2L);
        when(walkDAO.getWalk(walkId)).thenReturn(Collections.emptyList());
        ResponseEntity responseEntity = instance.updateWalk(0, walkId, null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}