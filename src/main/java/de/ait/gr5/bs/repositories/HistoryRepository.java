package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.History;
import de.ait.gr5.bs.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

  List<History> findAllBookByUser(User userId, Sort sort);
}
