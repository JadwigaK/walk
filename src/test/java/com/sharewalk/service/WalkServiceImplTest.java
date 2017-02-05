package com.sharewalk.service;

import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.dao.WalkDAOImpl;
import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class WalkServiceImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private WalkServiceImpl instance;

    @Mock
    private WalkDAO walkDAO;

    @Mock
    private UserDAO userDAO;

    @Test
    public void listWalksTest(){
        //given
        List walks = new ArrayList<>();
        when(walkDAO.listWalks()).thenReturn(walks);
        //when
        List<Walk> result = instance.listWalks();
        //then
        assertEquals(walks, result);
    }

    @Test
    public void getWalkTest(){
        //given
        Long walkId = Long.valueOf(1L);
        Walk walk = new Walk();
        when(walkDAO.getWalk(walkId)).thenReturn(walk);
        //when
        Walk result = instance.getWalk(walkId);
        //then
        assertEquals(walk, result);

    }

    @Test
    public void addNewWalkTest(){
        //given
        Long userId = Long.valueOf(1L);
        Walk walk = new Walk();
        User mockUser = mock(User.class);
        when(userDAO.getUserById(userId)).thenReturn(mockUser);
        //when
        instance.addNewWalk(walk, userId);
        //then
        verify(walkDAO).addNewWalk(walk);
        assertEquals(mockUser, walk.getUser());
    }




}
