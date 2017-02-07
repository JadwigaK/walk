package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
import com.sharewalk.model.WayPoint;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    public void listWalksWithoutParametersTest() {
        //Given
        Walk walk1 = new Walk("walk 1", new User("j", "p"), Arrays.asList(new WayPoint()));
        Walk walk2 = new Walk("walk 2", new User("k", "r"), Arrays.asList(new WayPoint()));
        when(entityManager.createNamedQuery("Walk.findAll")).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(walk1, walk2));
        //When
        List<Walk> list = instance.listWalks();
        //Then
        assertEquals(2, list.size());
        assertEquals(walk1, list.get(0));
        assertEquals(walk2, list.get(1));
    }

    @Test
    public void listWalksWithParametersTest() {
        //Given
        Walk walk1 = new Walk("walk 1", new User("j", "p"), Arrays.asList(new WayPoint()));
        when(entityManager.createNamedQuery("Walk.findAllStartsWith")).thenReturn(query);
        when(query.setParameter("startsWith", "walk 1" + "%")).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(walk1));
        //When
        List<Walk> list = instance.listWalks("walk 1");
        //Then
        assertEquals(1, list.size());
        assertEquals(walk1, list.get(0));
    }

    @Test
    public void getWalkTest() {
        //Given
        Walk walk1 = new Walk("walk 1", new User("j", "p"), Arrays.asList(new WayPoint()));
        Long id = 1L;
        when(entityManager.createNamedQuery("Walk.findByID")).thenReturn(query);
        when(query.setParameter("id", id)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(walk1));
        //When
        Walk actualWalk = instance.getWalk(id);
        //Then
        assertEquals(walk1, actualWalk);
    }


    @Test
    public void addNewWalkTest() {
        //Given
        Walk walk = new Walk();
        //When
        instance.addNewWalk(walk);
        //Then
        verify(entityManager).merge(walk);
    }

    @Test
    public void updateWalkTest() {
        //Given
        Walk walk = new Walk();
        //When
        instance.addNewWalk(walk);
        //Then
        verify(entityManager).merge(walk);
    }


}
