package end.dev.whatsap_clone.form_requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResetPasswordFormRequest {

    @NotBlank(message = "le mot de passe est obligatoir")
    @Size(min = 4, message = "Le mo de passe")
    private  String password;

    @NotBlank(message = "le nom d'utilisateur est obligatoire")
    private String username;


}
