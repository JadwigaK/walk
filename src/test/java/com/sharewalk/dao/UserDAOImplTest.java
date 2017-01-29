package com.sharewalk.dao;

import com.sharewalk.model.User;
import com.sharewalk.model.Walk;
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
    public void listUserWalksWithoutParametersTest(){
        Long userId = Long.valueOf(1L);
        when(entityManager.createNamedQuery("Walk.findAllForUser")).thenReturn(query);
        when(query.setParameter("userid",userId)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));

        List<Walk> list = instance.listUserWalks(userId);
        assertEquals(1, list.size());
        assertEquals(Walk.class, list.get(0).getClass());
    }

    @Test
    public void listUserWalksWithParametersTest(){
        Long userId = Long.valueOf(1L);
        String startsWith = "walk 1";
        when(entityManager.createNamedQuery("Walk.findAllForUserStartsWith")).thenReturn(query);
        when(query.setParameter("startsWith",startsWith+"%")).thenReturn(query1);
        when(query1.setParameter("userid",userId)).thenReturn(query2);
        when(query2.getResultList()).thenReturn(Arrays.asList(new Walk("walk 1", null, null)));

        List<Walk> list = instance.listUserWalks(userId, startsWith);
        assertEquals(1, list.size());
        assertEquals(Walk.class, list.get(0).getClass());
    }

    @Test
    public void addUserTest() {
        User user = new User();
        instance.addUser(user);
        verify(entityManager).persist(user);

    }

    @Test
    public void getUserByIDTest(){
        Long id = Long.valueOf(1L);
        when(entityManager.createNamedQuery("User.findUserByID")).thenReturn(query);
        when(query.setParameter("id",id)).thenReturn(query1);
        when(query1.getResultList()).thenReturn(Arrays.asList(new User("user1@", "pass1")));

        User actualUser = instance.getUserById(id);
        assertEquals(0, actualUser.getId());
        assertEquals("user1@", actualUser.getEmail());
        assertEquals("pass1", actualUser.getPassword());
    }
}
