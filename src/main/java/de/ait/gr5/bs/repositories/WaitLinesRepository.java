package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.models.WaitLine;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WaitLinesRepository extends JpaRepository<WaitLine, Long> {

  List<WaitLine> findAllByBook(Book book);

  List<WaitLine> findAllByUser(User user);

  String countByBook_BookId(Long bookId);
}
