package end.dev.whatsap_clone.repositories.messages;

import end.dev.whatsap_clone.entities.messages.ConversationMessage;
import end.dev.whatsap_clone.entities.messages.Conversations;
import end.dev.whatsap_clone.entities.messages.Messages;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationMessageRepository extends JpaRepository<ConversationMessage, Messages> {
    Page<ConversationMessage> findByConversationAndIsActive(Conversations conversations, Boolean isActive);
}
