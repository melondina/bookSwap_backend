package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;

public interface BooksRepository extends JpaRepository<Book, Long> {
  List<Book> findAllByOwner(User owner, Sort sort);
}
