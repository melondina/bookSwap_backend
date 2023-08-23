package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
