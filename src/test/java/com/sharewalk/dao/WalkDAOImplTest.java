package com.sharewalk.dao;

import com.sharewalk.model.Walk;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class WalkDAOImplTest {

    @InjectMocks
    private WalkDAOImpl instance;

    @Mock
    private EntityManager entityManager;

    @Mock
    Query query;

    @Mock
    Query query1;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listWalksWithoutParametersTest(){
        when(entityManager.createNamedQuery("Walk.findAll")).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null), new Walk("walk 2", null, null)));

        List<Walk> list = instance.listWalks();
        assertEquals(list.size(),2);
    }

    @Test
    public void listWalksWithParametersTest(){
        when(entityManager.createNamedQuery("Walk.findAllStartsWith")).thenReturn(query);
        when(query.setParameter("startsWith","walk 1"+"%")).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));

        List<Walk> list = instance.listWalks("walk 1");
        assertEquals(list.size(),1);
        assertEquals(list.get(0).toString(),"Walk [id=0, name=walk 1]");
    }

    @Test
    public void getWalkTest(){
        Long id = Long.valueOf(1);
        when(entityManager.createNamedQuery("Walk.findByID")).thenReturn(query);
        when(query.setParameter("id",id)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));

        List<Walk> list = instance.getWalk(id);
        assertEquals(list.size(),1);
        assertEquals(list.get(0).toString(),"Walk [id=0, name=walk 1]");
    }
}
