package catalog.kyrs.controller;

import catalog.kyrs.controller.request.CommentRequest;
import catalog.kyrs.exceptions.CinemaNotFoundException;
import catalog.kyrs.model.Comment;
import catalog.kyrs.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentRequest) throws CinemaNotFoundException {
        return ResponseEntity.ok(commentService.createComment(commentRequest));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/findByIdCinema")
    public ResponseEntity<List<Comment>> getCommentByCinemaId(@RequestParam("id") Long id) throws CinemaNotFoundException {
        return ResponseEntity.ok(commentService.getCommentsByCinema(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Comment> updatedCommentById(@PathVariable Long id, @RequestBody Comment updatedComment) {
        Optional<Comment> updatedCommentOptional = commentService.putCommentById(id, updatedComment);
        return updatedCommentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCommentById(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return ResponseEntity.ok().build();
    }
}
