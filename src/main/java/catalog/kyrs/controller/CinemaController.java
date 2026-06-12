package catalog.kyrs.controller;

import catalog.kyrs.controller.request.CinemaRequest;
import catalog.kyrs.exceptions.DirectorNotFoundException;
import catalog.kyrs.exceptions.ScreenwriterNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinemas")
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping
    public ResponseEntity<Cinema> createCinema(@RequestBody CinemaRequest cinemaRequest) throws DirectorNotFoundException, ScreenwriterNotFoundException {
        return ResponseEntity.ok(cinemaService.createCinema(cinemaRequest));
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> getAllCinemas() {
        return ResponseEntity.ok(cinemaService.getAllCinemas());
    }

    @GetMapping("/findByIdDirector")
    public ResponseEntity<List<Cinema>> getCinemasByDirectorId(@RequestParam("id") Long id) throws DirectorNotFoundException {
        return ResponseEntity.ok(cinemaService.getCinemasByDirector(id));
    }

    @GetMapping("/findByIdScreenwriter")
    public ResponseEntity<List<Cinema>> getCinemasByScreenwriterId(@RequestParam("id") Long id) throws ScreenwriterNotFoundException {
        return ResponseEntity.ok(cinemaService.getCinemasByScreenwriter(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Cinema> updatedCinemaById(@PathVariable Long id, @RequestBody Cinema updatedCinema) {
        Optional<Cinema> updatedCinemaOptional = cinemaService.putCinemaById(id, updatedCinema);
        return updatedCinemaOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCinemaById(@PathVariable Long id) {
        cinemaService.deleteCinemaById(id);
        return ResponseEntity.ok().build();
    }

}
