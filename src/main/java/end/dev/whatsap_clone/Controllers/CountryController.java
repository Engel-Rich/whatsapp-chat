package end.dev.whatsap_clone.Controllers;

import end.dev.whatsap_clone.Shared.ApiResponse;
import end.dev.whatsap_clone.Shared.EntityExtension;
import end.dev.whatsap_clone.entities.Country;
import end.dev.whatsap_clone.form_requests.CountryRequest;
import end.dev.whatsap_clone.services.CountryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/countries")
@RestController
@AllArgsConstructor
public class CountryController {

    private CountryService countryService;


    @PostMapping()
    public ResponseEntity<?> createCounty(@RequestBody(required = true) @Valid CountryRequest request){
      try {
          final Country country =   this.countryService.saveCountry(request.getLength(), request.getName()).orElseThrow();
          return  ApiResponse.created(country);
      }catch (Exception e) {
          return   ApiResponse.error(e.getMessage());
      }
    }

    @GetMapping()
    public  ResponseEntity<?> getCountries(
            @RequestParam(name = "size",defaultValue = "25",required = false) Integer size,
            @RequestParam(name = "page",defaultValue = "1",required = false) Integer page){
        try {
            final Page<Country>  response = this.countryService.getCountries(page,size);
            return EntityExtension.pageToResponse(response);
        }catch (Exception exception){
            return ApiResponse.error(exception.getMessage());
        }
    }
}
