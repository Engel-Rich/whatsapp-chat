package end.dev.whatsap_clone.Shared;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class PageResponseDTO<T> {
    @JsonProperty("current_page")
    private Integer currentPage ;
    private List<T> result;

    @JsonProperty("total_item")
    private  Integer totalItems;
}
