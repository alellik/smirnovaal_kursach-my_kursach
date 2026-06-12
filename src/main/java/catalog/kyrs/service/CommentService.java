package catalog.kyrs.service;

import catalog.kyrs.controller.request.CommentRequest;
import catalog.kyrs.exceptions.CinemaNotFoundException;
import catalog.kyrs.model.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment createComment(CommentRequest comment) throws CinemaNotFoundException;
    List<Comment> getAllComments();
    List<Comment> getCommentsByCinema(long id) throws CinemaNotFoundException;
    Optional<Comment> getCommentById(Long id);
    Optional<Comment> putCommentById(Long id, Comment updatedComment);
    void deleteCommentById(Long id);
}
