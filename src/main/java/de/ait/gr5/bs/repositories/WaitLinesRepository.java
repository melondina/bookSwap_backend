package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.models.WaitLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WaitLinesRepository extends JpaRepository<WaitLine, Long> {

  List<WaitLine> findAllByBook(Book book);

  List<WaitLine> findAllByUser(User user);

  @Query("SELECT w.user FROM WaitLine w WHERE w.book =:bookInit ORDER BY w.lineId ASC")
  User findTopByUser(@Param("bookInit") Book bookInit);

  String countByBook_BookId(Long bookId);
}
