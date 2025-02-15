package end.dev.whatsap_clone.Controllers;

import end.dev.whatsap_clone.Shared.ApiResponse;
import end.dev.whatsap_clone.components.JwtUtils;
import end.dev.whatsap_clone.entities.Authentication.AuthUser;
import end.dev.whatsap_clone.entities.Authentication.User;
import end.dev.whatsap_clone.form_requests.RefreshTokenRequest;
import end.dev.whatsap_clone.form_requests.ResetPasswordFormRequest;
import end.dev.whatsap_clone.form_requests.SignupRequest;
import end.dev.whatsap_clone.form_requests.LoginRequest;
import end.dev.whatsap_clone.services.UserServices;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {


    private final   PasswordEncoder encoder;
    private  final AuthenticationManager authenticationManager;
    private  final UserServices userServices;
    private  final JwtUtils jwtUtils;

    @PostMapping(path = "/register")
   public ResponseEntity<?> register(@RequestBody(required = true) @Valid SignupRequest request) {
        try {
            final User appUser = request.getUser();
            appUser.setPassword(encoder.encode(appUser.getPassword()));
            final User finalUser =userServices.savUserUser(appUser);
            final String accessTokenToken =  jwtUtils.createAccessToken(finalUser.getEmail());
            final String refreshToken =  jwtUtils.createRefreshToken(accessTokenToken);
            final AuthUser authUser = new AuthUser(
                    finalUser, accessTokenToken, refreshToken
            );

            return ApiResponse.ok(authUser);
        } catch (Exception e) {
            return ApiResponse.error(e.getMessage());
        }
    }

    @PostMapping("/login")
    public  ResponseEntity<?> login(@RequestBody(required = true) @Valid LoginRequest loginRequest) {
        final User appUser = userServices.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Nom d'utilisateur ou mot de passe incorecte"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
       final String accessTokenToken =  jwtUtils.createAccessToken(appUser.getEmail());
        final String refreshToken =  jwtUtils.createRefreshToken(accessTokenToken);
        final AuthUser authUser = new AuthUser(
                appUser, accessTokenToken, refreshToken
        );
        return ApiResponse.ok(authUser);
    }

    @PostMapping("/refresh_token")
    public  ResponseEntity<?> refreshToken(@RequestBody(required = true) @Valid  RefreshTokenRequest refreshTokenRequest){
        try{
            final String token = refreshTokenRequest.getToken();
            System.out.println(token);
            if(jwtUtils.isTokenExpired(token))  return ApiResponse.error("Invalid token", 401);
            final String accessToken = jwtUtils.extractEmail(token);
            System.out.println(accessToken);
            if(accessToken==null)  return ApiResponse.error("Invalid token", 401);
            final String email = jwtUtils.extractEmail(accessToken);
            if(email==null)  return ApiResponse.error("Invalid token", 401);
            System.out.println(email);
            HashMap<String, String> tokens = new HashMap<>();
            final String newAccessToken = jwtUtils.createAccessToken(email);
            tokens.put("access_token",newAccessToken );
            tokens.put("refresh_token", jwtUtils.createRefreshToken(newAccessToken));
            return  ApiResponse.ok(tokens);
        }catch (Exception exception){
            return  ApiResponse.error("Invalid token "+exception.getMessage(),401);
        }
    }

    @PutMapping("/reset_password")
    public  ResponseEntity<?> resetPassword(@RequestBody(required = true) @Valid ResetPasswordFormRequest passwordFormRequest){
        final String password = encoder.encode(passwordFormRequest.getPassword());
        final Boolean updatePassword = this.userServices.updatePassword(password, passwordFormRequest.getUsername());
        return  ApiResponse.ok(updatePassword);
    }

    @PutMapping("/update_password")
    public  ResponseEntity<?> changePassword(@RequestParam(required = true, name = "password") @NotBlank String password,
                                             @RequestParam(required =true, name = "old_password") @NotBlank String oldPassword ){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String email  = authentication.getName();
//        System.out.println(email);
        final User user = userServices.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found with this parameters"));
        if(!this.encoder.matches(oldPassword,user.getPassword())) throw new RuntimeException("invalid password");
        user.setPassword(this.encoder.encode(password));
        final Boolean updatePassword = this.userServices.updatePassword(user);
        return  ApiResponse.ok(updatePassword);
    }

    @GetMapping("/me")
    public  ResponseEntity<?> changePassword(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String email  = authentication.getName();
//        System.out.println(email);
        final User user = userServices.findByEmail(email).orElseThrow(()-> new RuntimeException("user not found with this parameters"));
        return  ApiResponse.ok(user);
    }

}
