package org.hotel.viewmodel;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hotel.model.Hotel;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Data
public class HotelViewModel {

    private String id;
    @NotBlank(message = "Name field is mandatory and must not be blank")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String name;

    @NotNull(message = "PricePerNight field is mandatory and must not be blank")
    @Min(value = 1, message = "PricePerNight  must be greater than 1€")
    @Max(value = 1000, message = "PricePerNight must be lesser than 1000€")
    private int pricePerNight;

    @Valid
    @NotNull(message = "Address is mandatory")
    private AddressViewModel address;

    @Valid
    private List<ReviewViewModel> reviews;

    protected HotelViewModel() {
        this.reviews = new ArrayList<>();
    }

    public Hotel toEntity() {
        return Hotel.builder()
                .id(id)
                .name(name)
                .pricePerNight(pricePerNight)
                .address(address.toEntity())
                .reviews(reviews.stream().map(ReviewViewModel::toEntity).collect(Collectors.toList()))
                .build();
    }
}
