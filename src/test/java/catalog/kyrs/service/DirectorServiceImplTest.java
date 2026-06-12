package catalog.kyrs.service;

import catalog.kyrs.model.Director;
import catalog.kyrs.repository.DirectorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DirectorServiceImplTest {

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    @Test
    void testCreateDirector() {
        Director directorToCreate = new Director();
        Director createdDirector = new Director();

        when(directorRepository.save(directorToCreate)).thenReturn(createdDirector);

        Director actualDirector = directorService.createDirector(directorToCreate);

        assertNotNull(actualDirector);
        assertEquals(createdDirector, actualDirector);
    }

    @Test
    void testGetAllDirectors() {
        List<Director> expectedDirectors = new ArrayList<>();

        when(directorRepository.findAll()).thenReturn(expectedDirectors);

        List<Director> actualDirectors = directorService.getAllDirectors();

        assertNotNull(actualDirectors);
        assertEquals(expectedDirectors, actualDirectors);
    }

    @Test
    void testGetDirectorById() {
        Long id = 1L;
        Director director = new Director();
        Optional<Director> expectedDirector = Optional.of(director);

        when(directorRepository.findById(id)).thenReturn(expectedDirector);

        Optional<Director> actualDirector = directorService.getDirectorById(id);

        assertTrue(actualDirector.isPresent());
        assertEquals(expectedDirector.get(), actualDirector.get());
    }

    @Test
    void testPutDirectorById() {
        Long id = 1L;
        Director existingDirector = new Director();
        Director updatedDirector = new Director();
        Optional<Director> expectedDirector = Optional.of(existingDirector);

        when(directorRepository.findById(id)).thenReturn(expectedDirector);

        Optional<Director> actualDirector = directorService.putDirectorById(id, updatedDirector);

        assertTrue(actualDirector.isPresent());
        assertEquals(existingDirector, actualDirector.get());
        verify(directorRepository, times(1)).save(existingDirector);
    }

    @Test
    void testDeleteDirectorById() {
        Long id = 1L;

        directorService.deleteDirectorById(id);

        verify(directorRepository, times(1)).deleteById(id);
    }
}
