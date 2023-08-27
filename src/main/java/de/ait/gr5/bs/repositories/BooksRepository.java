package de.ait.gr5.bs.repositories;

import de.ait.gr5.bs.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {}
