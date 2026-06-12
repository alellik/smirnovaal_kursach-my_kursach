package catalog.kyrs.service;

import catalog.kyrs.controller.request.CommentRequest;
import catalog.kyrs.exceptions.CinemaNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Comment;
import catalog.kyrs.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CinemaService cinemaService;

    @Override
    public Comment createComment(CommentRequest comment) throws CinemaNotFoundException {
        Optional<Cinema> cinema = cinemaService.getCinemaById(comment.getCinemaId());
        if (cinema.isPresent()) {
            return commentRepository.save(
                    new Comment(
                            null,
                            cinema.get(),
                            comment.getTextOfComment(),
                            new Date()
                    )
            );
        }
        else {
            throw new CinemaNotFoundException(comment.getCinemaId());
        }
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByCinema(long id) throws CinemaNotFoundException {
        Optional<Cinema> cinema = cinemaService.getCinemaById(id);
        if (cinema.isPresent()) {
            return commentRepository.findAllByCinema(cinema.get());
        }
        else {
            throw new CinemaNotFoundException(id);
        }
    }

    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Optional<Comment> putCommentById(Long id, Comment updatedComment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            Comment commentToUpdate = existingComment.get();
            if (updatedComment.getTextOfComment() != null) {
                commentToUpdate.setTextOfComment(updatedComment.getTextOfComment());
            }
            commentRepository.save(commentToUpdate);
        }
        return existingComment;
    }

    @Override
    public void deleteCommentById (Long id) {
        commentRepository.deleteById(id);
    }
}
