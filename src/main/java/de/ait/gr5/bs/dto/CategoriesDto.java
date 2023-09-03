package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "Category titles")
public class CategoriesDto {

  private List<CategoryDto> categories;

  public static CategoriesDto from(List<CategoryDto> categories) {
    return CategoriesDto.builder()
        .categories(categories)
        .build();
  }
}
