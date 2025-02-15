package end.dev.whatsap_clone.entities.Authentication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Rules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "grade")
    Integer grade;

}
