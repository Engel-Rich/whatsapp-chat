package end.dev.whatsap_clone.entities.Authentication;

import end.dev.whatsap_clone.entities.City;
import end.dev.whatsap_clone.entities.Country;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    public User(Country country, String username, String fullName, String email, String password) {
        this.country = country;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.profile ="";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active")
    private  boolean isActive;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", nullable = false, referencedColumnName = "id")
    private Country country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", nullable = true, referencedColumnName = "id")
    private City city;

    @Column(unique = true, nullable = false)
    private String username;


    @Column(unique = true, nullable = true, name = "full_name")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String profile;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = new Date();
    }

    @ManyToMany
    @JoinTable(
         name = "UsersRules",
         joinColumns = @JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private List<Rules> roles = new ArrayList<>();

    /**
     * @return List<String>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(rule-> new SimpleGrantedAuthority(rule.getName())).collect(Collectors.toList());
    }

    /**
     * @return Boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    /**
     * @return Boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    /**
     * @return Boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    /**
     * @return Boolean
     */
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
