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

  private String postalCode;
  private String city;

  public static LocationDto from(City city) {
    return LocationDto.builder()
        .postalCode(city.getPostalCode().toString())
        .city(city.getTitleCity())
        .build();
  }
}
