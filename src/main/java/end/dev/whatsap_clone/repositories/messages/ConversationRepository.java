package end.dev.whatsap_clone.repositories.messages;

import end.dev.whatsap_clone.entities.Authentication.User;
import end.dev.whatsap_clone.entities.messages.Conversations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ConversationRepository extends JpaRepository<Conversations,Long> {
        List<Conversations>  findBySenderAndReceiverAndIsActive(User sender, User Receiver, Boolean isActive);
}

