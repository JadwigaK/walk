package com.sharewalk.controller;

import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Comment;
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

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class WalkControllerTest {

    @InjectMocks
    private WalkController instance;

    @Mock
    private WalkDAO walkDAO;

    @Mock
    private WalkService walkService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    public void getAllWalksGeneralTest(){
        when(walkDAO.listWalks()).thenReturn(Arrays.asList(new Walk("walk 1", 1), new Walk("walk 2", 2) ));
        ResponseEntity responseEntity = instance.getAllWalks(null);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(((List)responseEntity.getBody()).size(),2);
    }

    @Test
    public void getAllWalksStartsWithTest(){
        when(walkDAO.listWalks("walk 1")).thenReturn(Arrays.asList(new Walk("walk 1", 1)));
        ResponseEntity responseEntity = instance.getAllWalks("walk 1");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(((List)responseEntity.getBody()).size(),1);
        assertEquals(((List)responseEntity.getBody()).get(0).toString(),"Walk [id=0, name=walk 1]");
    }

    @Test
    public void getWalkByIDTest(){
        Long id= Long.valueOf(1);
        when(walkDAO.getWalk(id)).thenReturn(Arrays.asList(new Walk("walk 1", 1)));
        ResponseEntity responseEntity = instance.getWalk(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(((List)responseEntity.getBody()).size(),1);
        assertEquals(((List)responseEntity.getBody()).get(0).toString(),"Walk [id=0, name=walk 1]");
    }

    @Test
    public void listCommentsTest(){
        Long id= Long.valueOf(1);
        when(walkDAO.getWalkComments(id)).thenReturn(Arrays.asList(new Comment("comment 1", 1, 1), new Comment("comment 2", 1, 2)));
        ResponseEntity responseEntity = instance.listComments(id);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(((List)responseEntity.getBody()).size(),2);
    }
}