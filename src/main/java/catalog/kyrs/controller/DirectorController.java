package catalog.kyrs.controller;

import catalog.kyrs.model.Director;
import catalog.kyrs.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/directors")
@RestController
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<Director> createDirector(@RequestBody Director director) {
        return ResponseEntity.ok(directorService.createDirector(director));
    }

    @GetMapping
    public ResponseEntity<List<Director>> getAllDirectors() {
        return ResponseEntity.ok(directorService.getAllDirectors());
    }

    @PutMapping("/{id}")
    ResponseEntity<Director> updatedDirectorById(@PathVariable Long id, @RequestBody Director updatedDirector) {
        Optional<Director> updatedDirectorOptional = directorService.putDirectorById(id, updatedDirector);
        return updatedDirectorOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDirectorById(@PathVariable Long id) {
        directorService.deleteDirectorById(id);
        return ResponseEntity.ok().build();
    }
}
