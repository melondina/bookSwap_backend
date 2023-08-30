package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BooksRepository extends JpaRepository<Book, Long> {
  List<Book> findAllByOwner(User owner, Sort sort);

  @Query("SELECT CONCAT(c.postalCode, ' ', c.title,' ', 'Germany') AS locationBook " +
      "FROM User u " +
      "JOIN u.ownedBooks b " +
      "JOIN u.city c " +
      "WHERE b.bookId = :bookId")
  String findLocationBook(@Param("bookId") Long bookId);
}
