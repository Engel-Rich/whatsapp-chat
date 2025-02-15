package end.dev.whatsap_clone.Shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean status;
    private String error;
    private Integer statusCode;
    private T data;


    public static <T> ResponseEntity<?> ok(T object) {
        ApiResponse<T>  response = new ApiResponse<>( );
        response.setStatus(true);
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(object);
        return ResponseEntity.ok().body(response);
    }
    public static <T> ResponseEntity<?> created(T object) {
        ApiResponse<T>  response = new ApiResponse<>( );
        response.setStatus(true);
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(object);
        return ResponseEntity.ok().body(response);
    }


    public static  <T> ResponseEntity<?> error(String error) {
      ApiResponse<T> response = new ApiResponse<>();
      response.setError(error);
      response.setStatusCode(400);
      response.setStatus(false);
      return  ResponseEntity.status(400).body(response);
    }
    public static  <T> ResponseEntity<?> error(String error, Integer statusCode) {
      ApiResponse<T> response = new ApiResponse<>();
      response.setError(error);
      response.setStatusCode(statusCode);
      response.setStatus(false);
      return  ResponseEntity.status(statusCode).body(response);
    }
}
