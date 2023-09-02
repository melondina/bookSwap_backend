package de.ait.gr5.bs.repositories;


import de.ait.gr5.bs.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationRepository extends JpaRepository<City, Long> {
  City findByPostalCode(String postalCode);

  @Query("SELECT CONCAT( c.postalCode,' ', c.title) FROM City c")
  List<String> findCityPostalCodeForFilter();

}
