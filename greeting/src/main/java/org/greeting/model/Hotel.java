package org.greeting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
@Document(collection = "hotels")
public class Hotel {
    @Id
    private String id;
    @NotBlank(message = "Name field is mandatory and must not be blank")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    private String name;
    @Indexed(direction = IndexDirection.ASCENDING)
    @NotNull(message = "PricePerNight field is mandatory and must not be blank")
    @Min(value = 1, message = "PricePerNight  must be greater than 1€")
    @Max(value = 1000, message = "PricePerNight must be lesser than 1000€")
    private int pricePerNight;
    @Valid
    @NotNull(message = "Address is mandatory")
    private Address address;
    @Valid
    private List<Review> reviews;

    protected Hotel() {
        this.reviews = new ArrayList<>();
    }
}
