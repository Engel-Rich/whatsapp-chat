package end.dev.whatsap_clone.services;

import end.dev.whatsap_clone.entities.Country;
import end.dev.whatsap_clone.repositories.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    public Optional<Country> saveCountry(Integer phoneLength, String name){
        System.out.println(name+ " length : "+ phoneLength);
        String url = API_URL + name;


        ParameterizedTypeReference<List<Map<String, Object>>> typeRef = new ParameterizedTypeReference<List<Map<String, Object>>>() {};

        List<Map<String, Object>> responseList = restTemplate.exchange(url, HttpMethod.GET, null, typeRef).getBody();

        assert responseList != null && !responseList.isEmpty();
        Map<String, Object> objectMap = responseList.get(0);

//        log.info("Response {}", objectMap);

        assert objectMap != null;
        if(objectMap.containsKey("name")){
          Country country =   Country.countryFromMap(objectMap);
          country.setPhoneLength(phoneLength);
          return Optional.of(countryRepository.save(country));
        }
        else if (objectMap.containsKey("status") && (Integer) objectMap.get("status")==400){
         throw  new RuntimeException("Impossible de trouve le pays avec le nom "+ name);
        }
        throw  new RuntimeException("Impossible de trouve le pays avec le nom "+ name);
    }

   public Country findById(Long id){
        return  this.countryRepository.findById(id).orElseThrow(()-> new RuntimeException("Aucun pays trouve avec cet id"));
    }

    public Page<Country> getCountries(Integer page, Integer size){
        final Pageable pageable = PageRequest.of(page-1,size);
        return this.countryRepository.findAll(pageable);
    }
}
