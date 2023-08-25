package de.ait.gr5.bs.controllers;

import de.ait.gr5.bs.controllers.api.BooksApi;
import de.ait.gr5.bs.dto.BookDetailDto;
import de.ait.gr5.bs.dto.BookNewDto;
import de.ait.gr5.bs.services.BooksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * 22.08.2023
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class BooksController implements BooksApi {

  BooksService booksService;

  @Override
  public ResponseEntity<BookDetailDto> addBook(BookNewDto newBook) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(booksService.addBook(newBook));
  }

}
