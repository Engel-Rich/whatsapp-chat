package end.dev.whatsap_clone.entities.messages;

import end.dev.whatsap_clone.entities.Authentication.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Table(name = "Messages")
@Data
@Entity
public class Messages {


    public Messages(String message, Conversations senderConversation, Conversations destConversation, MessageType messageType, User sender) {
        this.message = message;
        this.senderConversation = senderConversation;
        this.destConversation = destConversation;
        this.messageType = messageType;
        this.sender = sender;
    }

    public Messages(String message, Groupes groupes, MessageType messageType,User sender) {
        this.message = message;
        this.groupes = groupes;
        this.messageType = messageType;
        this.sender = sender;
    }

    public Messages(String message, Groupes groupes, MessageType messageType, User sender, List<MessageMedias> medias) {
        this.message = message;
        this.groupes = groupes;
        this.messageType = messageType;
        this.sender = sender;
        this.medias = medias;
    }

    public Messages(Groupes groupes, MessageType messageType, User sender, List<MessageMedias> medias) {
        this.groupes = groupes;
        this.messageType = messageType;
        this.sender = sender;
        this.medias = medias;
    }

    public Messages(String message, MessageType messageType, User sender, List<MessageMedias> medias, Conversations destConversation, Conversations senderConversation) {
        this.message = message;
        this.messageType = messageType;
        this.sender = sender;
        this.medias = medias;
        this.destConversation = destConversation;
        this.senderConversation = senderConversation;
    }


    public Messages(List<MessageMedias> medias, Conversations destConversation, Conversations senderConversation, User sender, MessageType messageType) {
        this.medias = medias;
        this.destConversation = destConversation;
        this.senderConversation = senderConversation;
        this.sender = sender;
        this.messageType = messageType;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "message")
    private  String message;

    @Column(name = "message_type")
    @Enumerated(EnumType.STRING)
    private  MessageType  messageType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupe_id",referencedColumnName = "id",nullable = true)
    private Groupes groupes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id",nullable = false,referencedColumnName = "id")
    private User sender;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_conversation_id", referencedColumnName = "id")
    private Conversations senderConversation;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dest_conversation_id", referencedColumnName = "id")
    private Conversations destConversation;


    @OneToMany(mappedBy = "messages",cascade = CascadeType.ALL)
    private  List<MessageMedias> medias;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    public  void  onCreate(){
        createdAt = new  Date();
        updatedAt = new Date();
    }
    @PreUpdate
    public  void  onUpdate(){
        updatedAt = new Date();
    }


}
