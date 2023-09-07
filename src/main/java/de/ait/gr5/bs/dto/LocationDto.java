package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.City;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Location")
public class LocationDto {
  @Schema(description = "Postal code", example = "13599")
  private String postalCode;
  @Schema(description = "Language ID", example = "Berlin")
  private String city;

  public static LocationDto from(City city) {
    return LocationDto.builder()
        .postalCode(city.getPostalCode())
        .city(city.getTitleCity())
        .build();
  }
}
