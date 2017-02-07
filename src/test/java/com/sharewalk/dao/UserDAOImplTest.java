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

public class UserDAOImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private UserDAOImpl instance;

    @Mock
    private EntityManager entityManager;

    @Mock
    Query query;

    @Mock
    Query query1;

    @Mock
    Query query2;

    @Test
    public void listUserWalksWithoutParametersTest() {
        //Given
        Walk walk1 = new Walk("walk 1", new User("j", "p"), Arrays.asList(new WayPoint()));
        Long userId = 1L;
        when(entityManager.createNamedQuery("Walk.findAllForUser")).thenReturn(query);
        when(query.setParameter("userid", userId)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(walk1));
        //When
        List<Walk> list = instance.listUserWalks(userId);
        //Then
        assertEquals(1, list.size());
        assertEquals(walk1, list.get(0));
    }

    @Test
    public void listUserWalksWithParametersTest() {
        //Given
        Walk walk1 = new Walk("walk 1", new User("j", "p"), Arrays.asList(new WayPoint()));
        Long userId = 1L;
        String startsWith = "walk 1";
        when(entityManager.createNamedQuery("Walk.findAllForUserStartsWith")).thenReturn(query);
        when(query.setParameter("startsWith", startsWith + "%")).thenReturn(query1);
        when(query1.setParameter("userid", userId)).thenReturn(query2);
        when(query2.getResultList()).thenReturn(Arrays.asList(walk1));
        //When
        List<Walk> list = instance.listUserWalks(userId, startsWith);
        //Then
        assertEquals(1, list.size());
        assertEquals(walk1, list.get(0));
    }

    @Test
    public void addUserTest() {
        //Given
        User user = new User();
        //When
        instance.addUser(user);
        //Then
        verify(entityManager).persist(user);
    }

    @Test
    public void getUserByIDTest() {
        //Given
        User user1 = new User("j", "p");
        Long id = 1L;
        when(entityManager.createNamedQuery("User.findUserByID")).thenReturn(query);
        when(query.setParameter("id", id)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(user1));
        //When
        User actualUser = instance.getUserById(id);
        //Then
        assertEquals(user1, actualUser);
    }
}
