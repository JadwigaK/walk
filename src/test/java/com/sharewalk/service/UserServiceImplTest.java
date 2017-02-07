package com.sharewalk.service;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private UserServiceImpl instance;

    @Mock
    private UserDAO userDAO;

    @Test
    public void listUserWalksTest() {
        //given
        Long userId = 1L;
        List<Walk> walks = new ArrayList<Walk>();
        when(userDAO.listUserWalks(userId)).thenReturn(walks);
        //when
        List<Walk> result = instance.listUserWalks(userId);
        //then
        assertEquals(walks, result);
    }


    @Test
    public void listUserByIdTest() {
        //given
        Long userId = 1L;
        User user = new User();
        when(userDAO.getUserById(userId)).thenReturn(user);
        //when
        User result = instance.getUserById(userId);
        //then
        assertEquals(user, result);
    }

    @Test
    public void addUserTest() {
        //given
        User user = new User();
        //when
        instance.addUser(user);
        //then
        verify(userDAO).addUser(user);
    }

}
