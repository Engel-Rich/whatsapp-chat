package end.dev.whatsap_clone.form_requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CountryRequest {

    @NotBlank(message = "Country name is required")
    private  String  name;

    @NotNull(message = "La taille du numero de telephone ne peut pas être nulle")
    @PositiveOrZero(message = "La taille du numero de telephone doit être un nombre positif")
    private  Integer length;
}
