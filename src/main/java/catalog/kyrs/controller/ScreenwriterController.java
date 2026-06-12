package catalog.kyrs.controller;

import catalog.kyrs.model.Screenwriter;
import catalog.kyrs.service.ScreenwriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/screenwriters")
@RestController
public class ScreenwriterController {

    private final ScreenwriterService screenwriterService;

    @PostMapping
    public ResponseEntity<Screenwriter> createScreenwriter(@RequestBody Screenwriter screenwriter) {
        return ResponseEntity.ok(screenwriterService.createScreenwriter(screenwriter));
    }

    @GetMapping
    public ResponseEntity<List<Screenwriter>> getAllScreenwriters() {
        return ResponseEntity.ok(screenwriterService.getAllScreenwriters());
    }

    @PutMapping("/{id}")
    ResponseEntity<Screenwriter> updatedScreenwriterById(@PathVariable Long id, @RequestBody Screenwriter updatedScreenwriter) {
        Optional<Screenwriter> updatedScreenwriterOptional = screenwriterService.putScreenwriterById(id, updatedScreenwriter);
        return updatedScreenwriterOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScreenwriterById(@PathVariable Long id) {
        screenwriterService.deleteScreenwriterById(id);
        return ResponseEntity.ok().build();
    }
}
