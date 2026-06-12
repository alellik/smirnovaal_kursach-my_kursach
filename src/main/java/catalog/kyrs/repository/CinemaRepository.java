package catalog.kyrs.repository;

import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Director;
import catalog.kyrs.model.Screenwriter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    List<Cinema> findAllByDirector(Director director);
    List<Cinema> findAllByScreenwriter(Screenwriter screenwriter);
}
