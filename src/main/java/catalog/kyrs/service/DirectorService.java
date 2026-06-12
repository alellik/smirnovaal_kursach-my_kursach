package catalog.kyrs.service;

import catalog.kyrs.model.Director;
import java.util.List;
import java.util.Optional;

public interface DirectorService {
    Director createDirector(Director director);
    List<Director> getAllDirectors();
    Optional<Director> getDirectorById(Long id);
    Optional<Director> putDirectorById(Long id, Director updatedDirector);
    void deleteDirectorById(Long id);
}
