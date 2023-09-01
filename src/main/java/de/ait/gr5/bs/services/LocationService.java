package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.LocationDto;

public interface LocationService {
  LocationDto getCity(String postCode);

}
