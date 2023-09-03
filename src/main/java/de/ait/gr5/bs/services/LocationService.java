package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.LocationDto;
import de.ait.gr5.bs.models.City;

public interface LocationService {
  LocationDto getCity(String postCode);

  City getOrCreatedCity(String postCode);

}
