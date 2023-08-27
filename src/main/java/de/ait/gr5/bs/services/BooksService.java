package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.BookDto;
import de.ait.gr5.bs.dto.BookNewDto;

public interface BooksService {
  BookDto addBook(BookNewDto newBook);

}
