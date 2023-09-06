package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BooksRepository extends JpaRepository<Book, Long> {
  @Query("SELECT CONCAT(c.postalCode, ' ', c.titleCity,' ', 'Germany') AS locationBook " +
      "FROM User u " +
      "JOIN u.ownedBooks b " +
      "JOIN u.city c " +
      "WHERE b.bookId = :bookId")
  String findLocationBook(@Param("bookId") Long bookId);

  @Query("SELECT DISTINCT b.language FROM Book b")
  List<String> findLanguageForFilter();

  @Query("SELECT DISTINCT c.titleCategory FROM Book b JOIN b.category c")
  List<String> findCategoryForFilter();

  @Query(value = "SELECT b FROM Book b " +
      "JOIN b.owner u " +
      "JOIN u.city c " +
      "JOIN b.category cat " +
      "WHERE (:userId IS NULL OR  u.userId  = :userId) " +
      "AND (:multiSearch IS NULL OR " +
      "     tsvector_match(:multiSearch) = true) " +
      "AND (:categoryId IS NULL OR  b.category.categoryId = :categoryId) " +
      "AND (:language IS NULL OR b.language = :language) " +
      "AND (:location IS NULL OR c.titleCity = :location) " +
      "ORDER BY b.bookId DESC")
  List<Book> findBooksByFilters(@Param("userId") Long  userId,
                                @Param("multiSearch") String multiSearch,
                                @Param("categoryId") Long categoryId,
                                @Param("language") String language,
                                @Param("location") String location);



}


