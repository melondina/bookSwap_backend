package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.WaitLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitLinesRepository extends JpaRepository<WaitLine, Long> {
}
