package end.dev.whatsap_clone.form_requests;

import end.dev.whatsap_clone.entities.Authentication.User;
import end.dev.whatsap_clone.entities.City;
import end.dev.whatsap_clone.entities.Country;
import end.dev.whatsap_clone.form_requests.validators.ExistDatabase;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class SignupRequest {

    public User getUser(){
        Country country = new Country(this.getCountry_id());
        return new User(
                country,
                this.getUsername(),
                this.getFullName(),
                this.getEmail(),
                this.getPassword()
        );
    }

    @NotNull(message = "Le pays de l'utilisateur ne peut etre null")
    @PositiveOrZero(message = "L'identifiant du pays ne peut etre negatif")
    @ExistDatabase(entity = Country.class, message = "Aucun pays trouve avec cet id", required = true)
    Long country_id;

    @Positive(message = "L'identifiant de la ville ne peut etre negatif")
    @ExistDatabase(entity = City.class,required = false,message = "Aucune ville trouve avec cet id")
    Integer city_id;

    @NotBlank(message = "le nom d'utilisateur ne peut etre null")
    @Size(min = 4, max = 20, message = "Le nom d'utilisatuer contient entre 4 et 20 caracteres")
    @Pattern(regexp = "^\\S+[a-zA-Z0-9_.-]*\\S+$", message = "le format de nom d'utilisateur est incorrect")
    String username;

    @Email(message = "adresse email invalide")
    @NotBlank(message = "Adresse email obligatoire")
    String email;

    @NotBlank(message = "le mot de passe ne peut etre null")
    @Size(min = 4, max = 35, message = "Le mot de passe contient entre 4 et 35 caracteres")
    String password;

    @Size(min = 4, max = 100, message = "Le  nom complet contient entre 4 et 20 caracteres")
    String fullName;


}


