package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.BookDto;
import de.ait.gr5.bs.dto.BookNewDto;
import de.ait.gr5.bs.dto.BookUpdateDto;
import de.ait.gr5.bs.handler.RestException;
import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.Category;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.repositories.BooksRepository;
import de.ait.gr5.bs.repositories.CategoriesRepository;
import de.ait.gr5.bs.repositories.UsersRepository;
import de.ait.gr5.bs.services.BooksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static de.ait.gr5.bs.dto.BookDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

  BooksRepository booksRepository;

  UsersRepository usersRepository;

  CategoriesRepository categoriesRepository;

  @Override
  public BookDto addBook(BookNewDto newBook) {
    User user = usersRepository.findById(newBook.getOwner())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "User with id <" + newBook.getOwner() + "> not found"));

    Category category = categoriesRepository.findById(newBook.getCategoryId())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Category with id <" + newBook.getCategoryId() + "> not found"));

    Book book = Book.builder()
        .title(newBook.getTitle())
        .author(newBook.getAuthor())
        .description(newBook.getDescription())
        .category(category)
        .language(newBook.getLanguage())
        .pages(newBook.getPages())
        .publisherDate(LocalDate.parse(newBook.getPublisherDate()))
        .cover(newBook.getCover())
        .dateCreate(java.time.LocalDate.now())
        .owner(user)
        .state(Book.State.AVAILABLE)
        .build();

    booksRepository.save(book);

    return from(book);
  }


  @Override
  public BookDto updateBook(Long bookId, BookUpdateDto updateBook) {
    Book book1 = booksRepository.findById(bookId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Book with id <" + bookId + "> not found"));

    User user = usersRepository.findById(updateBook.getOwner())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "User with id <" + updateBook.getOwner() + "> not found"));

    Category category = categoriesRepository.findById(updateBook.getCategoryId())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Category with id <" + updateBook.getCategoryId() + "> not found"));

    Book book = getBookOrThrow(bookId);
    book.setTitle(updateBook.getTitle());
    book.setAuthor(updateBook.getAuthor());
    book.setDescription(updateBook.getDescription());
    book.setCategory(category);
    book.setLanguage(updateBook.getLanguage());
    book.setPages(updateBook.getPages());
    book.setPublisherDate(LocalDate.parse(updateBook.getPublisherDate()));
    book.setCover(updateBook.getCover());
    book.setOwner(user);
    book.setDateCreate(book1.getDateCreate());
    book.setState(book1.getState());
    booksRepository.save(book);
    return from(book);
  }

  private Book getBookOrThrow(Long bookId) {
    return booksRepository.findById(bookId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND, "Book not found"));
  }

}
