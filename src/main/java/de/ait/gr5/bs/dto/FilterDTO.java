package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "List of categories, languages and locations for filters")
public class FilterDTO {
  @Schema(description = "List of languages")
  private List<LanguageDto> language;

  @Schema(description = "List of locations")
  private List<String> location;

  @Schema(description = "List of categories")
  private List<CategoryDto> category;

}
