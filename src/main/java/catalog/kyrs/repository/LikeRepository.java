package catalog.kyrs.repository;

import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByCinema(Cinema cinema);
    List<Like> findLikesByCinemaId(Long cinemaId);
    Like findLikesByCinemaIdAndUserId(long cinemaId, Long userId);
}
