package end.dev.whatsap_clone.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Country(String name, Integer phoneLength, String countryPhoneCode, String isoCode, String isoCode2,
            String currency) {
        this.name = name;
        this.phoneLength = phoneLength;
        this.countryPhoneCode = countryPhoneCode;
        this.isoCode = isoCode;
        this.isoCode2 = isoCode2;
        this.currency = currency;
    }

    public Country(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_length", nullable = false)
    private Integer phoneLength;

    @Column(name = "country_phone_code", nullable = false, unique = true)
    private String countryPhoneCode;

    @Column(name = "country_iso_code", nullable = true, unique = true)
    private String isoCode;

    @Column(name = "country_iso_code_2", nullable = true)
    private String isoCode2;

    @Column(name = "currency_code", nullable = true)
    private String currency;

    @Column(name = "flag", nullable = true)
    private String flag;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<City> cities;

    @PrePersist
    void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = new Date();
    }

    static public Country countryFromMap(Map<String, Object> map) {
        final Country country = new Country();

        String name = (String) ((Map<String, Object>) map.get("name")).get("common");
        country.setName(name);
        String isoCode = (String) map.get("cioc");
        country.setIsoCode(isoCode);
        Map<String, Object> map1 = (Map<String, Object>) map.get("currencies");
        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String currency = entry.getKey();
            country.setCurrency(currency);
        }
        String isoCode2 = (String) map.get("cca2");
        country.setIsoCode2(isoCode2);
        String flagMap = (String) ((Map<String, Object>) map.get("flags")).get("png");
        country.setFlag(flagMap);
        String phoneCode1 = (String) ((Map<String, Object>) map.get("idd")).get("root");
        List<String> phoneCode2 = (List<String>) ((Map<String, Object>) map.get("idd")).get("suffixes");

        country.setCountryPhoneCode(phoneCode1 + (phoneCode2.isEmpty() ? "" : phoneCode2.get(0)));
        return country;
    }

}
