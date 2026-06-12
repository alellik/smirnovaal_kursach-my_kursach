package catalog.kyrs.service;

import catalog.kyrs.controller.request.CinemaRequest;
import catalog.kyrs.exceptions.DirectorNotFoundException;
import catalog.kyrs.exceptions.ScreenwriterNotFoundException;
import catalog.kyrs.model.Cinema;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CinemaServiceTest {

    @Mock
    private CinemaService cinemaService;

    @Test
    void testCreateCinema() throws DirectorNotFoundException, ScreenwriterNotFoundException {
        CinemaRequest cinemaRequest = new CinemaRequest();
        Cinema cinema = new Cinema();
        when(cinemaService.createCinema(cinemaRequest)).thenReturn(cinema);
        assertEquals(cinema, cinemaService.createCinema(cinemaRequest));
    }

    @Test
    void getAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        when(cinemaService.getAllCinemas()).thenReturn(cinemas);
        assertEquals(cinemas, cinemaService.getAllCinemas());
    }

    @Test
    void getCinemasByDirector() throws DirectorNotFoundException {
        long directorId = 1L;
        List<Cinema> cinemasByDirector = new ArrayList<>();
        when(cinemaService.getCinemasByDirector(directorId)).thenReturn(cinemasByDirector);
        assertEquals(cinemasByDirector, cinemaService.getCinemasByDirector(directorId));
    }

    @Test
    void getCinemasByScreenwriter() throws ScreenwriterNotFoundException{
        long screenwriterId = 1L;
        List<Cinema> cinemasByScreenwriter = new ArrayList<>();
        when(cinemaService.getCinemasByScreenwriter(screenwriterId)).thenReturn(cinemasByScreenwriter);
        assertEquals(cinemasByScreenwriter, cinemaService.getCinemasByScreenwriter(screenwriterId));
    }

    @Test
    void putCinemaById() {
        Long id = 1L;
        Cinema updatedCinema = new Cinema();
        when(cinemaService.putCinemaById(id, updatedCinema)).thenReturn(Optional.of(updatedCinema));
        assertTrue(cinemaService.putCinemaById(id, updatedCinema).isPresent());
        assertEquals(updatedCinema, cinemaService.putCinemaById(id, updatedCinema).get());
    }

    @Test
    void getCinemaById() {
        Long id = 1L;
        Cinema cinema = new Cinema();
        when(cinemaService.getCinemaById(id)).thenReturn(Optional.of(cinema));
        assertTrue(cinemaService.getCinemaById(id).isPresent());
        assertEquals(cinema, cinemaService.getCinemaById(id).get());
    }

    @Test
    void deleteCinemaById() {
        Long id = 1L;
        cinemaService.deleteCinemaById(id);
        verify(cinemaService).deleteCinemaById(id);
    }
}