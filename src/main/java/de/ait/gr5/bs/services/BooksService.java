package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.*;

import java.util.List;

public interface BooksService {
  BookDto addBook(BookNewDto newBook);

  BookDto updateBook(Long bookId, BookUpdateDto updateBook);

  BooksShortDto getBooks(Long userId);
}
