package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Options for filtering the book list")
public class UserFilterSearchDTO {
  @Schema(description = "User ID", example = "1")
  private Long userId;

  @Schema(description = "Keywords for searching data by title, description and author of the book", example = "Living Sea of Waking")
  private String multiSearch;

  @Schema(description = "Category ID", example = "1")
  private Long categoryId;

  @Schema(description = "Language ID", example = "1")
  private Long languageId;

  @Schema(description = "City", example = "Berlin")
  private String location;
}
