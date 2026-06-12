package catalog.kyrs.service;

import catalog.kyrs.controller.request.CinemaRequest;
import catalog.kyrs.exceptions.DirectorNotFoundException;
import catalog.kyrs.exceptions.ScreenwriterNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Director;
import catalog.kyrs.model.Screenwriter;
import catalog.kyrs.repository.CinemaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final DirectorService directorService;
    private final ScreenwriterService screenwriterService;

    @Override
    public Cinema createCinema(CinemaRequest cinema) throws DirectorNotFoundException {
        Optional<Director> director = directorService.getDirectorById(cinema.getDirectorId());
        Optional<Screenwriter> screenwriter = screenwriterService.getScreenwriterById(cinema.getScreenwriterId());
        if (director.isPresent()) {
            return cinemaRepository.save(
                    new Cinema(
                            null,
                            director.get(),
                            screenwriter.get(),
                            cinema.getCinemaName(),
                            cinema.getCinemaDesc(),
                            cinema.getCinemaType(),
                            cinema.getNumberActors(),
                            cinema.getLikeScore()
                    )
            );
        } else {
            throw new DirectorNotFoundException(cinema.getDirectorId());
        }
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Optional<Cinema> putCinemaById(Long id, Cinema updatedCinema) {
        Optional<Cinema> existingCinema = cinemaRepository.findById(id);
        if (existingCinema.isPresent()) {
            Cinema cinemaToUpdate = existingCinema.get();
            if (updatedCinema.getCinemaName() != null) {
                cinemaToUpdate.setCinemaName(updatedCinema.getCinemaName());
            }
            if (updatedCinema.getCinemaDesc() != null) {
                cinemaToUpdate.setCinemaDesc(updatedCinema.getCinemaDesc());
            }
            if (updatedCinema.getCinemaType() != null) {
                cinemaToUpdate.setCinemaType(updatedCinema.getCinemaType());
            }
            if (updatedCinema.getNumberActors() != null) {
                cinemaToUpdate.setNumberActors(updatedCinema.getNumberActors());
            }
            if (updatedCinema.getLikeScore() != null) {
                cinemaToUpdate.setLikeScore(updatedCinema.getLikeScore());
            }
            cinemaRepository.save(cinemaToUpdate);
        }
        return existingCinema;
    }

    @Override
    public List<Cinema> getCinemasByDirector ( long id) throws DirectorNotFoundException {
        Optional<Director> director = directorService.getDirectorById(id);
        if (director.isPresent()) {
            return cinemaRepository.findAllByDirector(director.get());
        } else {
            throw new DirectorNotFoundException(id);
        }
    }

    @Override
    public List<Cinema> getCinemasByScreenwriter(long id) throws ScreenwriterNotFoundException {
        Optional<Screenwriter> screenwriter = screenwriterService.getScreenwriterById(id);
        if (screenwriter.isPresent()) {
            return cinemaRepository.findAllByScreenwriter(screenwriter.get());
        } else {
            throw new ScreenwriterNotFoundException(id);
        }
    }

    @Override
    public Optional<Cinema> getCinemaById (Long id){
        return cinemaRepository.findById(id);
    }

    @Override
    public void deleteCinemaById (Long id){
        cinemaRepository.deleteById(id);
    }
}
