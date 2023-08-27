package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Country")
public class CountryNewDto {

  @Schema(description = "Title of the country", example = "Germany")
  @NotNull
  @NotBlank
  private String title;

}
