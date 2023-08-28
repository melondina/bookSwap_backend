package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Error information")
public class ErrorDto {

    @Schema(description = "Error message", example = "User with this ID is not found")
    private String message;
}
