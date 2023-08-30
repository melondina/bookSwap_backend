package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.*;

public interface BooksService {
  BookDto addBook(BookNewDto newBook);

  BookDto updateBook(Long bookId, BookUpdateDto updateBook);

  BooksShortDto getBooks(Long userId);

  WaitLinePlaceDto addBookToUserBooks(Long bookId, Long userId);

  BookDto getBookDetail(Long bookId);
}
