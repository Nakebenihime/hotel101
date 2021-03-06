package org.hotel.model;

import lombok.Builder;
import lombok.Data;
import org.hotel.viewmodel.ReviewViewModel;

import javax.validation.constraints.*;

@Builder
@Data
public class Review {
    @NotBlank(message = "Email field must not be blank")
    @Email(message = "Email field must be a syntactically correct email address")
    private String email;
    @NotNull(message = "Rating field must not be empty")
    @Min(value = 1, message = "Rating must be greater than 0")
    @Max(value = 5, message = "Rating must be lesser than 6")
    private int rating;

    public ReviewViewModel toViewModel() {
        return ReviewViewModel.builder()
                .email(email)
                .rating(rating)
                .build();
    }
}
