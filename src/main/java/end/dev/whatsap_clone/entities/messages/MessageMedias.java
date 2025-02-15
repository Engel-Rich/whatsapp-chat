package end.dev.whatsap_clone.entities.messages;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "MessageMedias")
@Data
@Entity
public class MessageMedias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "media")
    String media;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "message_id",nullable = false)
    private  Messages messages;

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
