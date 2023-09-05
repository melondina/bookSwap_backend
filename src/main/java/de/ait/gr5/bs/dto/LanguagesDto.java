package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "Languages titles")
public class LanguagesDto {

  private List<LanguageDto> languages;

  public static LanguagesDto from(List<LanguageDto> languages) {
    return LanguagesDto.builder()
        .languages(languages)
        .build();
  }

}
