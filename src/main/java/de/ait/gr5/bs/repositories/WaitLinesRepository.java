package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.models.WaitLine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

public interface WaitLinesRepository extends JpaRepository<WaitLine, Long> {

  List<WaitLine> findAllByBook(Book book);

  List<WaitLine> findAllByUser(User user);

  WaitLine findTopByBook(Book book, Sort sort);

  String countByBook_BookId(Long bookId);

  @Transactional
  @Modifying
  @Query("DELETE FROM WaitLine e WHERE e.book.bookId = :bookId AND e.user.userId = :userId")
  int removeBookFromWaitLine(@Param("bookId") Long bookId, @Param("userId") Long userId);
  @Query("SELECT e FROM WaitLine e " +
      "WHERE e.book.bookId = :bookId AND e.user.userId = :userId")
  List<WaitLine> foundQueueUserAndBook(@Param("bookId") Long bookId,
                                       @Param("userId") Long userId);
}
