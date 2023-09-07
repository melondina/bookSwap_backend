package de.ait.gr5.bs.controllers;

import de.ait.gr5.bs.controllers.api.LocationApi;
import de.ait.gr5.bs.dto.LocationDto;
import de.ait.gr5.bs.services.LocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LocationController implements LocationApi {

  LocationService locationService;

  @Override
  public ResponseEntity<LocationDto> getCity(String postCode) {
    return ResponseEntity.ok().body(locationService.getCity(postCode));
  }
}
