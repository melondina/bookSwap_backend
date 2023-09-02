package de.ait.gr5.bs.controllers;

import de.ait.gr5.bs.controllers.api.BooksApi;
import de.ait.gr5.bs.dto.*;
import de.ait.gr5.bs.services.BooksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class BooksController implements BooksApi {

  BooksService booksService;

  @Override
  public ResponseEntity<BookDto> addBook(BookNewDto newBook) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(booksService.addBook(newBook));
  }

  @Override
  public ResponseEntity<BookDto> updateBook(Long bookId, BookUpdateDto updateBook) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(booksService.updateBook(bookId, updateBook));
  }

  @Override
  public ResponseEntity<BooksShortDto> getBooks(Long userId) {
    return ResponseEntity.ok()
        .body(booksService.getBooks(userId));
  }

  @Override
  public ResponseEntity<WaitLinePlaceDto> addBookToUserBooks(WaitLineRequestDto waitLineRequestDto) {
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(booksService.addBookToUserBooks(waitLineRequestDto));
  }

  @Override
  public ResponseEntity<BookDto> getBookDetail(Long bookId) {
    return ResponseEntity.ok().body(booksService.getBookDetail(bookId));
  }

  @Override
  public ResponseEntity<BooksShortDto> getHistory(Long userId){
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(booksService.getHistory(userId));
  }

  @Override
  public ResponseEntity<BooksShortDto> getWaitList(Long userId) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(booksService.getWaitList(userId));
  }

    @Override
    public ResponseEntity<FilterDTO> getFilter () {
      return ResponseEntity.ok().body(booksService.getFilter());
    }
  }