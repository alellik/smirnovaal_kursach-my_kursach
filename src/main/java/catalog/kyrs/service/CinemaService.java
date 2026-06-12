package catalog.kyrs.service;

import catalog.kyrs.controller.request.CinemaRequest;
import catalog.kyrs.exceptions.DirectorNotFoundException;
import catalog.kyrs.exceptions.ScreenwriterNotFoundException;
import catalog.kyrs.model.Cinema;
import java.util.List;
import java.util.Optional;

public interface CinemaService {

    Cinema createCinema(CinemaRequest cinema) throws DirectorNotFoundException;
    List<Cinema> getAllCinemas();
    List<Cinema> getCinemasByDirector(long id) throws DirectorNotFoundException;
    List<Cinema> getCinemasByScreenwriter(long id) throws ScreenwriterNotFoundException;
    Optional<Cinema> putCinemaById(Long id, Cinema updatedCinema);
    Optional<Cinema> getCinemaById(Long id);
    void deleteCinemaById(Long id);
}
