package catalog.kyrs.repository;

import org.springframework.data.repository.CrudRepository;
import catalog.kyrs.model.User;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
