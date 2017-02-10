package com.sharewalk.dao;

import com.sharewalk.model.Comment;
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

public class CommentDAOImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private CommentDAOImpl instance;

    @Mock
    private EntityManager entityManager;

    @Mock
    Query query;

    @Mock
    Query query1;

    @Mock
    Query query2;

    @Test
    public void listCommentsTest() {
        //given
        Long walkId = 1L;
        Comment comment1 = new Comment("com 1", new Walk(), new User());
        Comment comment2 = new Comment("com 2", new Walk(), new User());
        when(entityManager.createNamedQuery("Comment.findByWalkID")).thenReturn(query1);
        when(query1.setParameter("walkid", walkId)).thenReturn(query2);
        when(query2.getResultList()).thenReturn(Arrays.asList(comment1, comment2));
        //when
        List<Comment> list = instance.listComments(walkId);
        //then
        assertEquals(2, list.size());
        assertEquals(comment1, list.get(0));
        assertEquals(comment2, list.get(1));
    }

    @Test
    public void addCommentTest() {
        //Given
        Comment comment = new Comment();
        //When
        instance.addNewComment(comment);
        //Then
        verify(entityManager).persist(comment);
    }

}
