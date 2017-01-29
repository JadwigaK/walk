package com.sharewalk.controller;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private UserController instance;

    @Mock
    private UserDAO userDAO;

    @Test
    public void getUserWalksTest(){
        Long userId = Long.valueOf(1L);
        when(userDAO.listUserWalks(userId)).thenReturn(Arrays.asList(new Walk("walk 1", null, null), new Walk("walk 2", null, null) ));
        ResponseEntity responseEntity = instance.getUserWalks(userId, null);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, ((List)responseEntity.getBody()).size());
        assertEquals(Walk.class, ((List)responseEntity.getBody()).get(0).getClass());

    }

    @Test
    public void getUserWalksStartsWithTest(){
        Long userId = Long.valueOf(1L);
        when(userDAO.listUserWalks(userId)).thenReturn(Arrays.asList(new Walk("walk 1", null, null), new Walk("walk 2", null, null) ));
        when(userDAO.listUserWalks(userId,"walk 1" )).thenReturn(Arrays.asList(new Walk("walk 1", null, null) ));
        ResponseEntity responseEntity = instance.getUserWalks(userId, "walk 1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, ((List)responseEntity.getBody()).size());
        assertEquals(Walk.class, ((List)responseEntity.getBody()).get(0).getClass());
    }

    @Test
    public void getUserWalksUserNotFound(){
        Long userId= Long.valueOf(2L);
        when(userDAO.listUserWalks(userId)).thenReturn(Collections.emptyList());
        ResponseEntity responseEntity = instance.getUserWalks(userId, null);
        assertEquals( HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    public void addNewUserTest(){
        User user = new User("user 1", "pass1");
        ResponseEntity responseEntity = instance.addNewUser(user);
        verify(userDAO).addUser(user);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }




}
