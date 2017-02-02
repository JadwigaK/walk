package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WalkDAOImplTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private WalkDAOImpl instance;

    @Mock
    private EntityManager entityManager;

    @Mock
    Query query;

    @Mock
    Query query1;

    @Test
    public void listWalksWithoutParametersTest(){
        when(entityManager.createNamedQuery("Walk.findAll")).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null), new Walk("walk 2", null, null)));

        List walks =  new ArrayList<>();
        when(query.getResultList()).thenReturn(walks);

        List result = instance.listWalks();
        assertEquals(walks, result);

        List<Walk> list = instance.listWalks();
        assertEquals(2, list.size());
        assertEquals(Walk.class, list.get(0).getClass());
    }

    @Test
    public void listWalksWithParametersTest(){
        when(entityManager.createNamedQuery("Walk.findAllStartsWith")).thenReturn(query);
        when(query.setParameter("startsWith","walk 1"+"%")).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));

        List<Walk> list = instance.listWalks("walk 1");
        assertEquals(1, list.size());
        Walk actualWalk = (Walk)list.get(0);
        assertEquals(0, actualWalk.getId());
        assertEquals("walk 1", actualWalk.getName());
    }

    @Test
    public void getWalkTest(){
        Long id = Long.valueOf(1L);
        when(entityManager.createNamedQuery("Walk.findByID")).thenReturn(query);
        when(query.setParameter("id",id)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));

        Walk actualWalk = instance.getWalk(id);

        assertEquals(0, actualWalk.getId());
        assertEquals("walk 1", actualWalk.getName());
    }


    @Test
    public void addNewWalkTest(){
        Walk walk = new Walk();
        instance.addNewWalk(walk);
        verify(entityManager).merge(walk);
    }

    @Test
    public void updateWalkTest(){
        Walk walk = new Walk();
        instance.addNewWalk(walk);
        verify(entityManager).merge(walk);
    }


}
