package com.sharewalk.dao;

import com.sharewalk.controller.WalkController;
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
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class WalkDAOImplTest {

    @InjectMocks
    private WalkDAOImpl instance;

    @Mock
    private EntityManager entityManager;

    @Mock
    TypedQuery<Walk> query;

    @Mock
    TypedQuery<Walk> query1;

    @Mock
    TypedQuery<Comment> query3;

    @Mock
    TypedQuery<Comment> query4;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listWalksWithoutParametersTest(){
        when(entityManager.createNamedQuery("Walk.findAll", Walk.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", 1), new Walk("walk 2", 2)));

        List<Walk> list = instance.listWalks();
        assertEquals(list.size(),2);
    }

    @Test
    public void listWalksWithParametersTest(){
        when(entityManager.createNamedQuery("Walk.findAllStartsWith", Walk.class)).thenReturn(query);
        when(query.setParameter("startsWith","walk 1"+"%")).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", 1)));

        List<Walk> list = instance.listWalks("walk 1");
        assertEquals(list.size(),1);
        assertEquals(list.get(0).toString(),"Walk [id=0, name=walk 1]");
    }

    @Test
    public void getWalkTest(){
        Long id = Long.valueOf(1);
        when(entityManager.createNamedQuery("Walk.findByID", Walk.class)).thenReturn(query);
        when(query.setParameter("id",id)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", 1)));

        List<Walk> list = instance.getWalk(id);
        assertEquals(list.size(),1);
        assertEquals(list.get(0).toString(),"Walk [id=0, name=walk 1]");
    }

//    @Test
//    public void getWalkCommentsTest(){
//        Long id = Long.valueOf(1);
//        when(entityManager.createNamedQuery("Comment.findByIDComments", Comment.class)).thenReturn(query3);
//        when(query3.setParameter("walkid",id)).thenReturn(query4);
//        when(query4.getResultList()).thenReturn(Arrays.asList(new Comment("comment 1", 1, 1)));
//
//        List<Comment> list = instance.getWalkComments(id);
//        assertEquals(list.size(),1);
//        assertEquals(list.get(0).toString(),"Comment [id=0, comment=comment 1]");
//    }
}
