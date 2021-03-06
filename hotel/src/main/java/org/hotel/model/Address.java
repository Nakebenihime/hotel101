package org.hotel.model;

import lombok.Builder;
import lombok.Data;
import org.hotel.viewmodel.AddressViewModel;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class Address {
    @NotBlank(message = "City field must not be blank")
    private String city;
    @NotBlank(message = "Country field must not be blank")
    private String country;

    public AddressViewModel toViewModel() {
        return AddressViewModel.builder()
                .city(city)
                .country(country)
                .build();
    }
}
