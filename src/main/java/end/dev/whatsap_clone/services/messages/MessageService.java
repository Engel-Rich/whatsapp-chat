package end.dev.whatsap_clone.services.messages;

import end.dev.whatsap_clone.entities.messages.Conversations;
import end.dev.whatsap_clone.entities.messages.Messages;
import end.dev.whatsap_clone.repositories.messages.ConversationMessageRepository;
import end.dev.whatsap_clone.repositories.messages.ConversationRepository;
import end.dev.whatsap_clone.repositories.messages.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private  final MessageRepository messageRepository;
    private final ConversationMessageRepository conversationMessageRepository;
    private final ConversationRepository conversationRepository;

    public Messages sendMessage(Messages messages){

    }
}
