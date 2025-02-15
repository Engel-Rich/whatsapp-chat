package end.dev.whatsap_clone.configurations;

import end.dev.whatsap_clone.Shared.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//import java.util.HashMap;
//import java.util.Map;

@RestControllerAdvice
public class ErrorsAdvicesClass {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

        ex.getBindingResult().getFieldErrors().forEach(System.out::println);

        final String error = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        return ApiResponse.error("Erreur de validation " + error);
    }

    // Gestion des erreurs de type HttpMessageNotReadableException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
            WebRequest request) {
        return ApiResponse.error("Le corps de la requête est manquant ou mal formaté.");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?>  handlerExpireTokenException(){
        return  ApiResponse.error("Your are not authorize to access", 401);
    }

    // Gestion des autres exceptions générales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalExceptions(Exception ex, WebRequest request) {
        return ApiResponse.error(ex.getMessage());
    }

}
