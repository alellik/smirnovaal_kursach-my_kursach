package catalog.kyrs.service;

import catalog.kyrs.controller.request.CinemaRequest;
import catalog.kyrs.exceptions.DirectorNotFoundException;
import catalog.kyrs.exceptions.ScreenwriterNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Director;
import catalog.kyrs.model.Screenwriter;
import catalog.kyrs.repository.CinemaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CinemaServiceImplTest {

    @Mock
    private DirectorService directorService;

    @Mock
    private ScreenwriterService screenwriterService;

    @Mock
    private CinemaRepository cinemaRepository;

    @Mock
    private CinemaService cinemaService;

    @Test
    public void testCreateCinema() throws DirectorNotFoundException, ScreenwriterNotFoundException {
        Director director = new Director();
        Screenwriter screenwriter = new Screenwriter();

        CinemaRequest cinemaRequest = new CinemaRequest();
        cinemaRequest.setDirectorId(1L);
        cinemaRequest.setScreenwriterId(1L);
        cinemaRequest.setCinemaName("Test Cinema");
        cinemaRequest.setCinemaDesc("Test Description");
        cinemaRequest.setCinemaType("Test Type");
        cinemaRequest.setNumberActors(10);
        cinemaRequest.setLikeScore(4.5);

        Mockito.when(directorService.getDirectorById(1L)).thenReturn(Optional.of(director));
        Mockito.when(cinemaRepository.save(Mockito.any(Cinema.class))).thenReturn(new Cinema(1L, director, screenwriter, "Test Name", "Test Description", "Test Type", 10, 4.5));
        Cinema cinema = cinemaService.createCinema(cinemaRequest);
        assertEquals("Test Cinema", cinema.getCinemaName());
    }

    @Test
    public void testGetAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(new Cinema());
        Mockito.when(cinemaRepository.findAll()).thenReturn(cinemas);
        List<Cinema> result = cinemaService.getAllCinemas();
        assertEquals(1, result.size());
    }

    @Test
    public void testGetCinemasByDirector() throws DirectorNotFoundException {
        Director director = new Director();
        Mockito.when(directorService.getDirectorById(1L)).thenReturn(Optional.of(director));
        List<Cinema> cinemas = new ArrayList<>();
        cinemas.add(new Cinema());
        Mockito.when(cinemaRepository.findAllByDirector(director)).thenReturn(cinemas);
        List<Cinema> result = cinemaService.getCinemasByDirector(1L);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetCinemaById() {
        Cinema cinema = new Cinema(1L, new Director(), new Screenwriter(), "Test Name", "Test Description", "Test Type", 15, 4.5);
        Mockito.when(cinemaRepository.findById(1L)).thenReturn(Optional.of(cinema));
        Optional<Cinema> result = cinemaService.getCinemaById(1L);
        assertEquals("Test Cinema", result.get().getCinemaName());
    }

    @Test
    void createCinema_directorPresent_cinemaCreated() throws DirectorNotFoundException {
        CinemaRequest cinemaRequest = new CinemaRequest();
        Director director = new Director();
        Screenwriter screenwriter = new Screenwriter();

        when(directorService.getDirectorById(anyLong())).thenReturn(java.util.Optional.of(director));
        when(screenwriterService.getScreenwriterById(anyLong())).thenReturn(java.util.Optional.of(screenwriter));

        CinemaServiceImpl cinemaService = new CinemaServiceImpl(cinemaRepository, directorService, screenwriterService);
        Cinema createdCinema = cinemaService.createCinema(cinemaRequest);

        assertNotNull(createdCinema.getId());
        assertEquals(director, createdCinema.getDirector());
        assertEquals(screenwriter, createdCinema.getScreenwriter());
    }
}
