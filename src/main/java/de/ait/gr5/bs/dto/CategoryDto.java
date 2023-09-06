package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Category")
public class CategoryDto {

  @Schema(description = "Category ID", example = "1")
  private Long categoryId;

  @Schema(description = "Title of the category", example = "Esse")
  private String title;

  public static CategoryDto from(Category category) {
    return CategoryDto.builder()
        .categoryId(category.getCategoryId())
        .title(category.getTitleCategory())
        .build();
  }

  public static List<CategoryDto> from(List<Category> categories) {
    return categories.stream()
        .map(CategoryDto::from)
        .collect(Collectors.toList());
  }

}
