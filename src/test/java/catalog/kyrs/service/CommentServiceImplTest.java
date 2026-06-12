package catalog.kyrs.service;

import catalog.kyrs.controller.request.CommentRequest;
import catalog.kyrs.exceptions.CinemaNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Comment;
import catalog.kyrs.model.Director;
import catalog.kyrs.model.Screenwriter;
import catalog.kyrs.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CinemaService cinemaService;

    @Test
    void testCreateComment() throws CinemaNotFoundException {
        CommentRequest commentRequest = new CommentRequest(1L, "Great idea");
        Cinema cinema = new Cinema();
        Comment comment = new Comment();

        when(CinemaService.getCinemaById(1L)).thenReturn(Optional.of(cinema));
        when(commentRepository.save(any(Comment.class))).thenReturn(new Comment(1L, cinema, "Great idea", new Date()));

        CommentServiceImpl commentService = new CommentServiceImpl(commentRepository, cinemaService);
        Comment addedComment = commentService.createComment(commentRequest);

        assertEquals("Great idea", addedComment.getTextOfComment());
    }

    @Test
    void testGetAllComments() {
        when(commentRepository.findAll()).thenReturn(Arrays.asList(new Comment(), new Comment()));

        CommentServiceImpl commentService = new CommentServiceImpl(commentRepository, cinemaService);
        List<Comment> allComments = commentService.getAllComments();

        assertEquals(2, allComments.size());
    }

    @Test
    void testGetCommentsByCinema() throws CinemaNotFoundException {
        Cinema cinema = new Cinema(1L, new Director(), new Screenwriter(), "Name", "Desc", "Type", 15, 4.5);
        Comment comment = new Comment(1L, null, "Text", new Date());

        when(CinemaService.getCinemaById(1L)).thenReturn(Optional.of(cinema));
        when(commentRepository.findAllByCinema(cinema)).thenReturn(Arrays.asList(new Comment(), new Comment()));

        CommentServiceImpl commentService = new CommentServiceImpl(commentRepository, cinemaService);
        List<Comment> commentsByCinema = commentService.getCommentsByCinema(1L);

        assertEquals(2, commentsByCinema.size());
    }

    @Test
    void getCommentById() {
        Comment expectedComment = new Comment();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(expectedComment));

        CommentServiceImpl commentService = new CommentServiceImpl(commentRepository, cinemaService);
        Optional<Comment> commentById = commentService.getCommentById(1L);

        assertEquals(expectedComment, commentById.get());
    }
}