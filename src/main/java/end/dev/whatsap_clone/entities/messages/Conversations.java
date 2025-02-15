package end.dev.whatsap_clone.entities.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import end.dev.whatsap_clone.entities.Authentication.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Conversations")
public class Conversations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id")
    private User sender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dest_id")
    private User receiver;

    @Column(name = "unread_messages")
    private Integer unreadMessages;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_message_id")
    private  Messages lastMessage;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "senderConversation")
    @JsonIgnore
    private List<Messages>  messagesSends;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "destConversation")
    @JsonIgnore
    private List<Messages>  MessagesDest;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private  Date createdAt;

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
