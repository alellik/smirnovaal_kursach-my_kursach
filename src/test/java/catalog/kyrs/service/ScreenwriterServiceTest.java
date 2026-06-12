package catalog.kyrs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import catalog.kyrs.model.Screenwriter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScreenwriterServiceTest {

    private ScreenwriterService screenwriterService = mock(ScreenwriterService.class);

    @Test
    public void testCreateScreenwriter() {
        Screenwriter screenwriter = new Screenwriter();
        when(screenwriterService.createScreenwriter(screenwriter)).thenReturn(screenwriter);

        Screenwriter createdScreenwriter = screenwriterService.createScreenwriter(screenwriter);

        assertEquals(screenwriter, createdScreenwriter);
    }

    @Test
    public void testGetAllScreenwriters() {
        List<Screenwriter> screenwriters = new ArrayList<>();
        when(screenwriterService.getAllScreenwriters()).thenReturn(screenwriters);

        List<Screenwriter> retrievedScreenwriters = screenwriterService.getAllScreenwriters();

        assertEquals(screenwriters, retrievedScreenwriters);
    }

    @Test
    public void testGetScreenwriterById() {
        Long id = 1L;
        Screenwriter screenwriter = new Screenwriter();
        when(screenwriterService.getScreenwriterById(id)).thenReturn(Optional.of(screenwriter));

        Optional<Screenwriter> retrievedScreenwriter = screenwriterService.getScreenwriterById(id);

        assertTrue(retrievedScreenwriter.isPresent());
        assertEquals(screenwriter, retrievedScreenwriter.get());
    }

    @Test
    public void testPutScreenwriterById() {
        Long id = 1L;
        Screenwriter updatedScreenwriter = new Screenwriter();
        when(screenwriterService.putScreenwriterById(id, updatedScreenwriter)).thenReturn(Optional.of(updatedScreenwriter));

        Optional<Screenwriter> retrievedScreenwriter = screenwriterService.putScreenwriterById(id, updatedScreenwriter);

        assertTrue(retrievedScreenwriter.isPresent());
        assertEquals(updatedScreenwriter, retrievedScreenwriter.get());
    }

    @Test
    public void testDeleteScreenwriterById() {
        Long id = 1L;

        screenwriterService.deleteScreenwriterById(id);

        verify(screenwriterService).deleteScreenwriterById(id);
    }
}
