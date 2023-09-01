package de.ait.gr5.bs.repositories;


import de.ait.gr5.bs.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<City, Long> {
  City findByPostalCode(String postalCode);

}
