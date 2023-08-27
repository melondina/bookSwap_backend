package de.ait.gr5.bs.controllers;

import de.ait.gr5.bs.controllers.api.BooksApi;
import de.ait.gr5.bs.dto.BookDto;
import de.ait.gr5.bs.dto.BookNewDto;
import de.ait.gr5.bs.dto.BookUpdateDto;
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

  //@PreAutorize("hasAuthority('USER','ADMIN'")
  @Override
  public ResponseEntity<BookDto> addBook(BookNewDto newBook) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(booksService.addBook(newBook));
  }

  //@PreAutorize("hasAuthority('USER','ADMIN'")
  @Override
  public ResponseEntity<BookDto> updateBook (Long bookId, BookUpdateDto updateBook){
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(booksService.updateBook(bookId, updateBook));
  }

}
