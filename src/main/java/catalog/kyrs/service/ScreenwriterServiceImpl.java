package catalog.kyrs.service;

import catalog.kyrs.model.Screenwriter;
import catalog.kyrs.repository.ScreenwriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScreenwriterServiceImpl implements ScreenwriterService {

    private final ScreenwriterRepository screenwriterRepository;

    @Override
    public Screenwriter createScreenwriter(Screenwriter screenwriter) {
        return screenwriterRepository.save(screenwriter);
    }

    @Override
    public List<Screenwriter> getAllScreenwriters() {
        return screenwriterRepository.findAll();
    }

    @Override
    public Optional<Screenwriter> getScreenwriterById(Long id) {
        return screenwriterRepository.findById(id);
    }

    @Override
    public Optional<Screenwriter> putScreenwriterById(Long id, Screenwriter updatedScreenwriter) {
        Optional<Screenwriter> existingScreenwriter = screenwriterRepository.findById(id);
        if (existingScreenwriter.isPresent()) {
            Screenwriter screenwriterToUpdate = existingScreenwriter.get();
            if (updatedScreenwriter.getNameScreenwriter() != null) {
                screenwriterToUpdate.setNameScreenwriter(updatedScreenwriter.getNameScreenwriter());
            }
            if (updatedScreenwriter.getSurnameScreenwriter() != null) {
                screenwriterToUpdate.setSurnameScreenwriter(updatedScreenwriter.getSurnameScreenwriter());
            }
            screenwriterRepository.save(screenwriterToUpdate);
        }
        return existingScreenwriter;
    }

    @Override
    public void deleteScreenwriterById (Long id) {
        screenwriterRepository.deleteById(id);
    }
}
