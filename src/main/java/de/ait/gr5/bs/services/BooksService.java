package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.BookDetailDto;
import de.ait.gr5.bs.dto.BookNewDto;

public interface BooksService {
  BookDetailDto addBook(BookNewDto newBook);

}
