package catalog.kyrs.service;

import catalog.kyrs.model.Director;
import catalog.kyrs.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    @Override
    public Director createDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Optional<Director> getDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    @Override
    public Optional<Director> putDirectorById(Long id, Director updatedDirector) {
        Optional<Director> existingDirector = directorRepository.findById(id);
        if (existingDirector.isPresent()) {
            Director directorToUpdate = existingDirector.get();
            if (updatedDirector.getNameDirector() != null) {
                directorToUpdate.setNameDirector(updatedDirector.getNameDirector());
            }
            if (updatedDirector.getSurnameDirector() != null) {
                directorToUpdate.setSurnameDirector(updatedDirector.getSurnameDirector());
            }
            directorRepository.save(directorToUpdate);
        }
        return existingDirector;
    }

    @Override
    public void deleteDirectorById (Long id) {
        directorRepository.deleteById(id);
    }
}
