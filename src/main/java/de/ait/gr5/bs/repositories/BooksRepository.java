package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BooksRepository extends JpaRepository<Book, Long> {
  List<Book> findAllByOwner(User owner);

  @Query("SELECT CONCAT(c.postalCode, ' ', c.titleCity,' ', 'Germany') AS locationBook " +
      "FROM User u " +
      "JOIN u.ownedBooks b " +
      "JOIN u.city c " +
      "WHERE b.bookId = :bookId")
  String findLocationBook(@Param("bookId") Long bookId);

  @Query(value = "SELECT b FROM Book b " +
      "JOIN b.owner u " +
      "JOIN u.city c " +
      "WHERE (:userId IS NULL OR  u.userId  = :userId) " +
      "AND (:multiSearch IS NULL OR " +
      "     tsvector_match(:multiSearch) = true) " +
      "AND (:categoryId IS NULL OR  b.category.categoryId = :categoryId) " +
      "AND (:languageId IS NULL OR b.language.languageId = :languageId) " +
      "AND (:location IS NULL OR c.titleCity = :location) " +
      "ORDER BY b.bookId DESC")
  List<Book> findBooksByFilters(@Param("userId") Long userId,
                                @Param("multiSearch") String multiSearch,
                                @Param("categoryId") Long categoryId,
                                @Param("languageId") Long languageId,
                                @Param("location") String location);


}
