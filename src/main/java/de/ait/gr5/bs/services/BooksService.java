package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.BookDto;
import de.ait.gr5.bs.dto.BookNewDto;
import de.ait.gr5.bs.dto.BookUpdateDto;

public interface BooksService {
  BookDto addBook(BookNewDto newBook);

  BookDto updateBook(Long bookId, BookUpdateDto updateBook);

}
