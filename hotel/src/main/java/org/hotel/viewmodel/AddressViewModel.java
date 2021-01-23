package org.hotel.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hotel.model.Address;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressViewModel {

    @NotBlank(message = "City field must not be blank")
    private String city;

    @NotBlank(message = "Country field must not be blank")
    private String country;

    public Address toEntity() {
        return Address.builder()
                .city(city)
                .country(country)
                .build();
    }
}