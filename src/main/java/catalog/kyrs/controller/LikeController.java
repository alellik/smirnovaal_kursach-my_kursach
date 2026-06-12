package catalog.kyrs.controller;

import catalog.kyrs.controller.request.LikeRequest;
import catalog.kyrs.exceptions.LikeNotFoundException;
import catalog.kyrs.model.Like;
import catalog.kyrs.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody LikeRequest likeRequest) throws LikeNotFoundException {
        return ResponseEntity.ok(likeService.createLike(likeRequest));
    }

    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes() {
        return ResponseEntity.ok(likeService.getAllLikes());
    }

    @GetMapping("/findByIdCinema")
    public ResponseEntity<List<Like>> getLikesByCinemaId(@RequestParam("id") Long id) throws LikeNotFoundException {
        return ResponseEntity.ok(likeService.getLikesByCinema(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Like> updatedLikeById(@PathVariable Long id, @RequestBody Like updatedLike) {
        Optional<Like> updatedLikeOptional = likeService.putLikeById(id, updatedLike);
        return updatedLikeOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteLikeById(@PathVariable Long id) {
        likeService.deleteLikeById(id);
        return ResponseEntity.ok().build();
    }
}
