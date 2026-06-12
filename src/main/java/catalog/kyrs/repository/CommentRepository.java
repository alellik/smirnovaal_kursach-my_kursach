package catalog.kyrs.repository;

import catalog.kyrs.model.Cinema;
import catalog.kyrs.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCinema(Cinema cinema);
}
