package catalog.kyrs.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import catalog.kyrs.controller.request.CinemaRequest;
import catalog.kyrs.exceptions.DirectorNotFoundException;
import catalog.kyrs.exceptions.ScreenwriterNotFoundException;
import catalog.kyrs.model.Cinema;
import catalog.kyrs.service.CinemaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CinemaControllerTest {

    @Mock
    private CinemaService cinemaService;

    private CinemaController cinemaController;

    @Test
    public void testCreateCinema() throws DirectorNotFoundException, ScreenwriterNotFoundException {
        CinemaRequest cinemaRequest = new CinemaRequest();
        Cinema cinema = new Cinema();
        when(cinemaService.createCinema(any(CinemaRequest.class))).thenReturn(cinema);

        ResponseEntity<Cinema> response = cinemaController.createCinema(cinemaRequest);

        assertEquals(cinema, response.getBody());
    }

    @Test
    public void testGetAllCinemas() {
        List<Cinema> cinemas = new ArrayList<>();
        when(cinemaService.getAllCinemas()).thenReturn(cinemas);

        ResponseEntity<List<Cinema>> response = cinemaController.getAllCinemas();

        assertEquals(cinemas, response.getBody());
    }

    @Test
    public void testGetCinemasByDirectorId() throws DirectorNotFoundException {
        Long directorId = 1L;
        List<Cinema> cinemas = new ArrayList<>();
        when(cinemaService.getCinemasByDirector(directorId)).thenReturn(cinemas);

        ResponseEntity<List<Cinema>> response = cinemaController.getCinemasByDirectorId(directorId);

        assertEquals(cinemas, response.getBody());
    }

    @Test
    public void testGetCinemasByScreenwriterId() throws ScreenwriterNotFoundException {
        Long screenwriterId = 1L;
        List<Cinema> cinemas = new ArrayList<>();
        when(cinemaService.getCinemasByScreenwriter(screenwriterId)).thenReturn(cinemas);

        ResponseEntity<List<Cinema>> response = cinemaController.getCinemasByScreenwriterId(screenwriterId);

        assertEquals(cinemas, response.getBody());
    }

    @Test
    public void testUpdateCinemaById() {
        Long id = 1L;
        Cinema updatedCinema = new Cinema();
        when(cinemaService.putCinemaById(eq(id), any(Cinema.class))).thenReturn(Optional.of(updatedCinema));

        ResponseEntity<Cinema> response = cinemaController.updatedCinemaById(id, updatedCinema);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(updatedCinema, response.getBody());
    }

    @Test
    public void testDeleteCinemaById() {
        Long id = 1L;

        ResponseEntity<Void> response = cinemaController.deleteCinemaById(id);

        verify(cinemaService).deleteCinemaById(id);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
