package end.dev.whatsap_clone.repositories;

import end.dev.whatsap_clone.entities.Authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    List<User> findByEmailOrUsername(String email, String username );
}
