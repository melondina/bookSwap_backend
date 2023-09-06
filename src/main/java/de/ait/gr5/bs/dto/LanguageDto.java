package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.Language;
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
@Schema(description = "Language")
public class LanguageDto {

  @Schema(description = "Language ID", example = "1")
  private Long languageId;

  @Schema(description = "Title of the language", example = "Esse")
  private String title;

  public static LanguageDto from(Language lang) {
    return LanguageDto.builder()
        .languageId(lang.getLanguageId())
        .title(lang.getTitle())
        .build();
  }

  public static List<LanguageDto> from(List<Language> languages) {
    return languages.stream()
        .map(LanguageDto::from)
        .collect(Collectors.toList());
  }

}
