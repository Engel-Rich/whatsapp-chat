package end.dev.whatsap_clone.entities.Authentication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthUser {

    private User user;
    private  String accessToken;
    private  String refreshToken;
}
