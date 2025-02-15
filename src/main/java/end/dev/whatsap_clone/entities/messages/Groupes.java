package end.dev.whatsap_clone.entities.messages;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Groupes")

public class Groupes {

    public Groupes(String name) {
        this.name = name;
    }

    public Groupes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private  String  description;

    @Column(name = "profile")
    private String profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "last_message_id")
    private  Messages lastMessage;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "groupes")
    @JsonIgnore
    private List<Messages> messagesList;

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
