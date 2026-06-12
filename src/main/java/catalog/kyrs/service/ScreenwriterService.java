package catalog.kyrs.service;

import catalog.kyrs.model.Screenwriter;
import java.util.List;
import java.util.Optional;

public interface ScreenwriterService {
    Screenwriter createScreenwriter(Screenwriter screenwriter);
    List<Screenwriter> getAllScreenwriters();
    Optional<Screenwriter> getScreenwriterById(Long id);
    Optional<Screenwriter> putScreenwriterById(Long id, Screenwriter updatedScreenwriter);
    void deleteScreenwriterById(Long id);
}
