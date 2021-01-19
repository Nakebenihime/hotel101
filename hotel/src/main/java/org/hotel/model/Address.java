package org.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @NotBlank(message = "City field must not be blank")
    private String city;
    @NotBlank(message = "Country field must not be blank")
    private String country;
}
