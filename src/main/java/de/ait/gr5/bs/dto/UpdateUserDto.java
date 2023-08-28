package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Data for update")
@Builder
public class UpdateUserDto {

    @Schema(description = "Users role - USER - user", example = "USER")
    private String newRole;

    @Schema(description = "Users status - NOT_CONFIRMED, CONFIRMED, DELETED", example = "CONFIRMED")
    private String newState;

    @Schema(description = "Users firstname", example = "Peter")
    private String firstName;

    @Schema(description = "Users lastname", example = "Wolf")
    private String lastName;

    // country, city?
   // private String country;

    @Schema(description = "Users postal code", example = "23654")
    private Integer postalCode;
}