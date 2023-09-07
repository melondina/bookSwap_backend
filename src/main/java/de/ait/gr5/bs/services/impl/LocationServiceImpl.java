package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.LocationDto;
import de.ait.gr5.bs.externalApi.ExternalApiService;
import de.ait.gr5.bs.handler.RestException;
import de.ait.gr5.bs.models.City;
import de.ait.gr5.bs.repositories.LocationRepository;
import de.ait.gr5.bs.services.LocationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LocationServiceImpl implements LocationService {
  LocationRepository locationRepository;
  ExternalApiService externalApiService;

  @Override
  public LocationDto getCity(String postCode) {
    City city = getOrCreatedCity(postCode);
    if (city != null) {
      return LocationDto.from(city);
    } else {
      throw new RestException(HttpStatus.NOT_FOUND,
          "City with postal code " + postCode + " not found");
    }
  }

  public City getOrCreatedCity(String postCode) {
    City city = locationRepository.findByPostalCode(postCode);
    if (city != null && !city.getTitleCity().isEmpty()) {
      return city;
    } else {
      City externalCity = externalApiService.getCityByPostalCode(postCode);
      if (externalCity != null) {
        locationRepository.save(externalCity);
        return externalCity;
      } else {
       return null;
      }
    }
  }
}
