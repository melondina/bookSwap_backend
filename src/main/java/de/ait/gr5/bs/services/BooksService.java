package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.*;

public interface BooksService {
  BookDto addBook(BookNewDto newBook);

  BookDto updateBook(Long bookId, BookUpdateDto updateBook);

  BooksShortDto getBooks(Long userId);

  WaitLinePlaceDto addBookToUserBooks(WaitLineRequestDto waitLineRequestDto);

  BookDto getBookDetail(Long bookId);

  BooksShortDto getHistory(Long userId);

  BooksShortDto getWaitList(Long userId);

  FilterDTO getFilter();

  BooksShortDto getSendList(Long userId);

  WaitLineNextUserDto getInfoAboutNextReaderInLine(WaitLineRequestDto waitLineRequestDto);

  AllUserBooksDto sendBookToNextUser(WaitLineRequestDto waitLineRequestDto);

  AllUserBooksDto getAllUserBooksInfo(Long userId);
}
