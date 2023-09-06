package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.*;

public interface BooksService {
  BookDto addBook(BookNewDto newBook);

  BookDto updateBook(Long bookId, BookUpdateDto updateBook);

  BooksShortDto getBooks(UserFilterSearchDTO filterForSearch);

  WaitLinePlaceDto addBookToUserBooks(WaitLineRequestDto waitLineRequestDto);

  BookDto getBookDetail(Long bookId);

  BooksShortDto getHistory(Long userId);

  BooksShortDto getWaitList(Long userId);

  FilterDTO getFilter();
}
