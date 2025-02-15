package end.dev.whatsap_clone.Shared;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public class EntityExtension {

    public  static <T> ResponseEntity<?> pageToResponse(Page<T> tPage) {
        PageResponseDTO<T> response =   new PageResponseDTO<T>(
                tPage.getNumber(),
                tPage.getContent(),
                tPage.getContent().size()
        );
        System.out.println(response);
        return  ApiResponse.ok(response);
    }

}
