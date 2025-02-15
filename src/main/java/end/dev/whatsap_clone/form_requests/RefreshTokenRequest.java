package end.dev.whatsap_clone.form_requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class RefreshTokenRequest {

    @NotBlank(message = "Le token est obligatoire")
    private String token;

}
