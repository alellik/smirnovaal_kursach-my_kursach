package catalog.kyrs.service;

import catalog.kyrs.controller.request.CommentRequest;
import catalog.kyrs.exceptions.CinemaNotFoundException;
import catalog.kyrs.model.Comment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentService commentService;

    @Test
    void testCreateComment() throws CinemaNotFoundException {
        CommentRequest mockRequest = new CommentRequest(null, "Great idea!");
        Comment mockComment = new Comment(1L, null, "Great idea!", new Date());
        when(commentService.createComment(mockRequest)).thenReturn(mockComment);
        assertEquals(mockComment, commentService.createComment(mockRequest));
    }

    @Test
    void testGetAllComments() {
        List<Comment> mockComments = Arrays.asList(
                new Comment(1L, null, "Good idea", new Date()),
                new Comment(2L, null, "Must idea", new Date())
        );
        when(commentService.getAllComments()).thenReturn(mockComments);
        assertEquals(mockComments, commentService.getAllComments());
    }

    @Test
    void getCommentsByCinema() throws CinemaNotFoundException {
        long cinemaId = 1L;
        List<Comment> mockComments = Collections.singletonList(
                new Comment(1L, null, "Excellent idea", new Date())
        );
        when(commentService.getCommentsByCinema(cinemaId)).thenReturn(mockComments);
        assertEquals(mockComments, commentService.getCommentsByCinema(cinemaId));
    }

    @Test
    void getCommentById() {
        Long commentId = 1L;
        Comment mockComment = new Comment(1L, null, "Informative comment", new Date());
        when(commentService.getCommentById(commentId)).thenReturn(Optional.of(mockComment));
        assertTrue(commentService.getCommentById(commentId).isPresent());
        assertEquals(mockComment, commentService.getCommentById(commentId).get());
    }

    @Test
    void putCommentById() {
        Long commentId = 1L;
        Comment updatedComment = new Comment(1L, null, "Updated comment", new Date());
        when(commentService.putCommentById(commentId, updatedComment)).thenReturn(Optional.of(updatedComment));
        assertTrue(commentService.putCommentById(commentId, updatedComment).isPresent());
        assertEquals(updatedComment, commentService.putCommentById(commentId, updatedComment).get());
    }

    @Test
    void deleteCommentById() {
        Long commentId = 1L;
        commentService.deleteCommentById(commentId);
        verify(commentService, times(1)).deleteCommentById(commentId);
    }
}