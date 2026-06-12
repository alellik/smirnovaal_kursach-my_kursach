package catalog.kyrs.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import catalog.kyrs.model.Screenwriter;
import catalog.kyrs.service.ScreenwriterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScreenwriterControllerTest {

    @Mock
    private ScreenwriterService screenwriterService;

    private ScreenwriterController screenwriterController;


    @Test
    public void testCreateScreenwriter() {
        Screenwriter screenwriter = new Screenwriter();
        when(screenwriterService.createScreenwriter(any(Screenwriter.class))).thenReturn(screenwriter);

        ResponseEntity<Screenwriter> response = screenwriterController.createScreenwriter(screenwriter);

        assertEquals(screenwriter, response.getBody());
    }

    @Test
    public void testGetAllScreenwriters() {
        List<Screenwriter> screenwriters = new ArrayList<>();
        when(screenwriterService.getAllScreenwriters()).thenReturn(screenwriters);

        ResponseEntity<List<Screenwriter>> response = screenwriterController.getAllScreenwriters();

        assertEquals(screenwriters, response.getBody());
    }

    @Test
    public void testUpdateScreenwriterById() {
        Long id = 1L;
        Screenwriter updatedScreenwriter = new Screenwriter();
        when(screenwriterService.putScreenwriterById(eq(id), any(Screenwriter.class))).thenReturn(Optional.of(updatedScreenwriter));

        ResponseEntity<Screenwriter> response = screenwriterController.updatedScreenwriterById(id, updatedScreenwriter);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(updatedScreenwriter, response.getBody());
    }

    @Test
    public void testDeleteScreenwriterById() {
        Long id = 1L;

        ResponseEntity<Void> response = screenwriterController.deleteScreenwriterById(id);

        verify(screenwriterService).deleteScreenwriterById(id);
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}
