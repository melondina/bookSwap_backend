package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Category")
public class CategoryNewDto {

  @Schema(description = "Title of the category", example = "Esse")
  @NotNull
  @NotBlank
  private String title;

}
