package de.ait.gr5.bs.controllers.api;


import de.ait.gr5.bs.dto.LocationDto;
import de.ait.gr5.bs.dto.StandardResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/location")
@Tags(value =
@Tag(name = "Location"))
public interface LocationApi {

  @Operation(summary = "Title city", description = "Search for a city by postal code")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "404", description = "City not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "200", description = "Found city according to postal code",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = LocationDto.class))
          })
  })
  @GetMapping("/{postal_code}")
  ResponseEntity<LocationDto> getCity(@Parameter(required = true, description = "Postal code", example = "13599")
                                      @PathVariable(name = "postal_code") String postalCode);

}
