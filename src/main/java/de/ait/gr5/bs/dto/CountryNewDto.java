package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.Country;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Country")
public class CountryNewDto {

  @Schema(description = "Country ID", example = "1")
  private Long id;

  @Schema(description = "Title of the country", example = "Germany")
  private String title;

  public static CountryNewDto from(Country country) {
    return CountryNewDto.builder()
        .title(country.getTitle())
        .build();
  }

}
