package catalog.kyrs.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import catalog.kyrs.model.Director;
import catalog.kyrs.service.DirectorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DirectorControllerTest {

    @Mock
    private DirectorService directorService;

    private DirectorController directorController;

    @Test
    public void testCreateDirector() {
        Director director = new Director();
        when(directorService.createDirector(any(Director.class))).thenReturn(director);

        ResponseEntity<Director> response = directorController.createDirector(director);

        assertEquals(director, response.getBody());
    }

    @Test
    public void testGetAllDirectors() {
        List<Director> directors = new ArrayList<>();
        when(directorService.getAllDirectors()).thenReturn(directors);

        ResponseEntity<List<Director>> response = directorController.getAllDirectors();

        assertEquals(directors, response.getBody());
    }

    @Test
    public void testUpdateDirectorById() {
        Long id = 1L;
        Director updatedDirector = new Director();
        when(directorService.putDirectorById(eq(id), any(Director.class))).thenReturn(Optional.of(updatedDirector));

        ResponseEntity<Director> response = directorController.updatedDirectorById(id, updatedDirector);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(updatedDirector, response.getBody());
    }

    @Test
    public void testDeleteDirectorById() {
        Long id = 1L;

        ResponseEntity<Void> response = directorController.deleteDirectorById(id);

        verify(directorService).deleteDirectorById(id);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
