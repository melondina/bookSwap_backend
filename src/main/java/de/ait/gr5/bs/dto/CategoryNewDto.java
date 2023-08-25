package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Category")
public class CategoryNewDto {

  @Schema(description = "Category ID", example = "1")
  private Long categoryId;

  @Schema(description = "Title of the category", example = "Esse")
  private String title;

  public static CategoryNewDto from(Category category) {
    return CategoryNewDto.builder()
        .title(category.getTitle())
        .build();
  }

}
