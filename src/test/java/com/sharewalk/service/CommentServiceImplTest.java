package com.sharewalk.service;

import com.sharewalk.dao.CommentDAO;
import com.sharewalk.dao.UserDAO;
import com.sharewalk.dao.WalkDAO;
import com.sharewalk.model.Comment;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentServiceImplTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @InjectMocks
    private CommentServiceImpl instance;

    @Mock
    private CommentDAO commentDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private WalkDAO walkDAO;

    @Test
    public void addNewCommentTest(){
        //given
        Comment mockComment = mock(Comment.class);
        Long userId = 1L;
        Long walkId = 2L;
        //when
        instance.addNewComment(mockComment, userId, walkId);
        //then
        verify(commentDAO).addNewComment(mockComment);
    }


    @Test
    public void listCommentsTest(){
        //given
        List<Comment> comments = new ArrayList<>();
        Long userId = 2L;
        Long walkId = 2L;
        User mockUser = mock(User.class);
        Walk mockWalk = mock(Walk.class);
        when(userDAO.getUserById(userId)).thenReturn(mockUser);
        when(walkDAO.getWalk(walkId)).thenReturn(mockWalk);
        //when
        List<Comment> list = instance.listComments(walkId);
        //then
        verify(commentDAO).listComments(walkId);
        assertEquals(comments, list);
    }
}
