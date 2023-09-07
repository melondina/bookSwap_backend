package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Data for update")
@Builder
public class UpdateUserDto {

  @Schema(description = "Users firstname", example = "Peter")
  private String firstName;

  @Schema(description = "Users lastname", example = "Wolf")
  private String lastName;

  @Schema(description = "Users postal code", example = "23654")
  private String postalCode;
}