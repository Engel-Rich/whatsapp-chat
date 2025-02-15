package end.dev.whatsap_clone.repositories.messages;

import end.dev.whatsap_clone.entities.messages.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages, Long> {

}
