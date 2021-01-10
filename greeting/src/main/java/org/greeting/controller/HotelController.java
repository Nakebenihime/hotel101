package org.greeting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.greeting.model.ApiError;
import org.greeting.model.Hotel;
import org.greeting.service.HotelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;


@Validated
@Tag(name = "hotel", description = "Hotel API operations")
@RestController
@RequestMapping("${api.uri.paths.hotel}")
public class HotelController {

    private HotelServiceImpl hotelServiceImpl;

    @Autowired
    public void setHotelService(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }

    @Operation(summary = "Register a new hotel to the system", description = "Register a new hotel to the system", tags = "hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The request to create a resource was successful and response data was returned. The location header contains the created resource's URI.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "400", description = "The request is invalid and cannot be accepted.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "415", description = "The request cannot be accepted because the media type of the request entity is not a format the API understands. Make sure the Content-Type header in the request is application/json.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "500", description = "The request failed. An Error occurred on the server side.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class)))})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> save(@Valid @RequestBody Hotel hotel, UriComponentsBuilder uriComponentsBuilder) {
        this.hotelServiceImpl.save(hotel);
        UriComponents uriComponents = uriComponentsBuilder.path("/api/v1/hotels/{id}").buildAndExpand(hotel.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .location(uriComponents.toUri())
                .body(hotel);
    }

    @Operation(summary = "Get all available hotels", description = "Get all available hotels", tags = "hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was successful and response data was returned.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "500", description = "The request failed. An Error occurred on the server side.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hotel>> getAllHotels() {
        return ResponseEntity.ok(hotelServiceImpl.findAll());
    }

    @Operation(summary = "Gets the Hotel for the given hotelId", description = "Gets the Hotel for the given hotelId", tags = "hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was successful and response data was returned.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "The request cannot be fulfilled because the resource does not exist.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "The request failed. An Error occurred on the server side.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class)))})
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Hotel>> getHotelById(@Parameter(description = "Hotel identification number") @PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.hotelServiceImpl.findById(id));
    }

    @Operation(summary = "Updates an existing hotel", description = "Updates an existing hotel", tags = "hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was successful and response data was returned.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "400", description = "The request is invalid and cannot be accepted.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "The request cannot be fulfilled because the resource does not exist.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "415", description = "The request cannot be accepted because the media type of the request entity is not a format the API understands. Make sure the Content-Type header in the request is application/json.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "500", description = "The request failed. An Error occurred on the server side.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class)))})
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Hotel> update(@Valid @RequestBody Hotel hotel, @Parameter(description = "Hotel identification number") @PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.hotelServiceImpl.updateById(hotel, id));
    }

    @Operation(summary = "Deletes the Hotel for the given hotelId", description = "Deletes the Hotel for the given hotelId", tags = "hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The request was successful and response data was returned",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "The request cannot be fulfilled because the resource does not exist.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "500", description = "The request failed. An Error occurred on the server side.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiError.class)))})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteHotelById(@Parameter(description = "Hotel identification number") @PathVariable String id) {
        this.hotelServiceImpl.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @GetMapping(value = "/price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Hotel>> getAllHotelsByPricePerNightMax(@RequestParam @Min(1) int min, @RequestParam @Max(9999) int max) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.hotelServiceImpl.findByPricePerNightBetween(min, max));
    }
}