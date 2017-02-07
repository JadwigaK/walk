package com.sharewalk.controller;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import com.sharewalk.service.UserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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
    private UserService userService;

    @Test
    public void getUserWalksTest() {
        //given
        Long userId = 1L;
        User mockUser = mock(User.class);
        List<Walk> walks = new ArrayList<Walk>();
        when(userService.getUserById(userId)).thenReturn(mockUser);
        when(userService.listUserWalks(userId)).thenReturn(walks);
        //when
        ResponseEntity responseEntity = instance.getUserWalks(userId, null);
        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walks, (List) responseEntity.getBody());

    }

    @Test
    public void getUserWalksStartsWithTest() {
        //given
        Long userId = 1L;
        User mockUser = mock(User.class);
        List<Walk> walks = new ArrayList<Walk>();
        when(userService.listUserWalks(userId, "walk 1")).thenReturn(walks);
        when(userService.getUserById(userId)).thenReturn(mockUser);
        //when
        ResponseEntity responseEntity = instance.getUserWalks(userId, "walk 1");
        //then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(walks, (List) responseEntity.getBody());
    }

    @Test
    public void getUserWalksUserNotFound() {
        //given
        Long userId = 2L;
        when(userService.listUserWalks(userId)).thenReturn(Collections.emptyList());
        //when
        ResponseEntity responseEntity = instance.getUserWalks(userId, null);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

    }

    @Test
    public void addNewUserTest() {
        //given
        User mockUser = mock(User.class);
        //when
        ResponseEntity responseEntity = instance.addNewUser(mockUser);
        //then
        verify(userService).addUser(mockUser);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
