package com.sharewalk.controller;

import com.sharewalk.model.Comment;
import com.sharewalk.model.Walk;
import com.sharewalk.service.CommentService;
import com.sharewalk.service.WalkService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommentControllerTest {
    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Rule
    public ExpectedException ex = ExpectedException.none();

    @InjectMocks
    private CommentController instance;

    @Mock
    private CommentService commentService;

    @Mock
    private WalkService walkService;

    @Test
    public void addCommentTest() {
        //given
        Long userId = 1L;
        Long walkId = 1L;
        Comment mockComment = mock(Comment.class);
        //when
        ResponseEntity responseEntity = instance.addComment(userId, walkId, mockComment);
        //then
        verify(commentService).addNewComment(mockComment, userId, walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getWalkCommentsNotFound() {
        //given
        Long walkId = 2L;
        when(walkService.getWalk(walkId)).thenReturn(null);
        //when
        ResponseEntity responseEntity = instance.listComments(walkId);
        //then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getWalkCommentsOK() {
        //given
        Long walkId = 2L;
        Walk walk = new Walk();
        when(walkService.getWalk(walkId)).thenReturn(walk);
        //when
        ResponseEntity responseEntity = instance.listComments(walkId);
        //then
        verify(commentService).listComments(walkId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
