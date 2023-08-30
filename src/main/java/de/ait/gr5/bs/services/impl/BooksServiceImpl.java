package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.*;
import de.ait.gr5.bs.dto.UserDto;
import de.ait.gr5.bs.handler.RestException;
import de.ait.gr5.bs.models.Book;
import de.ait.gr5.bs.models.Category;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.models.WaitLine;
import de.ait.gr5.bs.repositories.BooksRepository;
import de.ait.gr5.bs.repositories.CategoriesRepository;
import de.ait.gr5.bs.repositories.UsersRepository;
import de.ait.gr5.bs.repositories.WaitLinesRepository;
import de.ait.gr5.bs.security.details.SecurityService;
import de.ait.gr5.bs.services.BooksService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static de.ait.gr5.bs.dto.BookDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

  BooksRepository booksRepository;
  UsersRepository usersRepository;
  CategoriesRepository categoriesRepository;
  WaitLinesRepository waitLinesRepository;

  private final SecurityService securityService;
  public static final Sort SORT_BY_DATA_CREATED_DESC = Sort.by(Sort.Direction.DESC, "dateCreate");


  @Override
  public BookDto addBook(BookNewDto newBook) {
    User user = getUserOrElseThrow(newBook.getOwner());

    Category category = getCategoryOrElseThrow(newBook.getCategoryId());

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
    Book book1 = getBookOrElseThrow(bookId);

    User user = getUserOrElseThrow(updateBook.getOwner());

    Category category = getCategoryOrElseThrow(updateBook.getCategoryId());

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


  @Override
  public BooksShortDto getBooks(Long userId) {
    List<Book> books;
    if (userId == null) {
      books = booksRepository.findAll(SORT_BY_DATA_CREATED_DESC);
    } else {
      if (!securityService.isUserAuthorized(userId)) {
        throw new RestException(HttpStatus.FORBIDDEN, "Not have permission to access this resource");
      } else {
        User user = getUserOrElseThrow(userId);
        books = booksRepository.findAllByOwner(user, SORT_BY_DATA_CREATED_DESC);
      }
    }
    return BooksShortDto.from(BookShortDto.from(books));
  }

  public Category getCategoryOrElseThrow(Long categoryId) {
    Category category = categoriesRepository.findById(categoryId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Category with id <" + categoryId + "> not found"));
    return category;
  }

  public Book getBookOrElseThrow(Long bookId) {
    Book book1 = booksRepository.findById(bookId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Book with id <" + bookId + "> not found"));
    return book1;
  }

  public Book getBookOrThrow(Long bookId) {
    return booksRepository.findById(bookId).orElseThrow(
        () -> new RestException(HttpStatus.NOT_FOUND, "Book not found"));
  }

  public User getUserOrElseThrow(Long userId) {
    User user = usersRepository.findById(userId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "User with id <" + userId + "> not found"));
    return user;
  }

  @Override
  public WaitLinePlaceDto addBookToUserBooks(Long bookId, Long userId) {
    User user = getUserOrElseThrow(userId);
    Book book = getBookOrElseThrow(bookId);

    //todo - if the user not have a permission
    //todo - if the user already have that book

    WaitLine waitLine = WaitLine.builder()
            .book(book)
            .user(user)
            .dateCreate(LocalDate.from(LocalDateTime.now()))
            .build();

    waitLinesRepository.save(waitLine);

    List<WaitLine> checkTheNumbers = waitLinesRepository.findAllByBook(book);

    return WaitLinePlaceDto.from(checkTheNumbers, waitLine);
  }
}
