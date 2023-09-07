package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.RegistrationDto;
import de.ait.gr5.bs.dto.UserDto;
import de.ait.gr5.bs.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequestMapping("/api/registration")
@Tags(value =
@Tag(name = "Registration")
)
public interface RegistrationApi {

  @Operation(summary = "Create a user", description = "Available to everyone")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User is created",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
          }),
      @ApiResponse(responseCode = "400", description = "Validation error",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
          })
  })
  @PostMapping
  ResponseEntity<UserDto> registration(@RequestBody @Valid RegistrationDto registerData);
}
