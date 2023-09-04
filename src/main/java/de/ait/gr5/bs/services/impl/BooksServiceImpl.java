package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.*;
import de.ait.gr5.bs.handler.RestException;
import de.ait.gr5.bs.models.*;
import de.ait.gr5.bs.repositories.*;
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
import java.util.*;
import java.util.stream.Collectors;

import static de.ait.gr5.bs.dto.BookDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

  BooksRepository booksRepository;
  UsersRepository usersRepository;
  CategoriesRepository categoriesRepository;
  WaitLinesRepository waitLinesRepository;
  HistoryRepository historyRepository;
  LocationRepository locationRepository;

  private final SecurityService securityService;
  public static final Sort SORT_BY_DATA_CREATED_DESC = Sort.by(Sort.Direction.DESC, "dateCreate");
  public static final Sort SORT_BY_ID_DESC = Sort.by(Sort.Direction.DESC, "id");

  public static final Sort SORT_BY_ID = Sort.by(Sort.Direction.ASC, "lineId");


  @Override
  public BookDto addBook(BookNewDto newBook) {
    User user = getUserOrElseThrow(newBook.getOwner());

    Category category = getCategoryOrElseThrow(newBook.getCategoryId());

    Book book;
    if (user.getState().equals(User.State.NOT_CONFIRMED)) {
      throw new RestException(HttpStatus.FORBIDDEN, "Fill in full details about yourself in your profile");
    } else if (!securityService.isUserPermission(user.getUserId())) {
      throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
    } else {
      book = Book.builder()
          .title(newBook.getTitle())
          .author(newBook.getAuthor())
          .description(newBook.getDescription())
          .category(category)
          .language(newBook.getLanguage())
          .pages(newBook.getPages())
          .publisherDate(newBook.getPublisherDate())
          .cover(newBook.getCover())
          .dateCreate(java.time.LocalDate.now())
          .owner(user)
          .state(Book.State.AVAILABLE)
          .build();

      booksRepository.save(book);
    }
    return from(book);
  }


  @Override
  public BookDto updateBook(Long bookId, BookUpdateDto updateBook) {
    Book book1 = getBookOrElseThrow(bookId);

    User user = getUserOrElseThrow(updateBook.getOwner());

    Category category = getCategoryOrElseThrow(updateBook.getCategoryId());

    Book book;
    if (!securityService.isUserPermission(user.getUserId())) {
      throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
    } else {
      book = getBookOrElseThrow(bookId);
      book.setTitle(updateBook.getTitle());
      book.setAuthor(updateBook.getAuthor());
      book.setDescription(updateBook.getDescription());
      book.setCategory(category);
      book.setLanguage(updateBook.getLanguage());
      book.setPages(updateBook.getPages());
      book.setPublisherDate(updateBook.getPublisherDate());
      book.setCover(updateBook.getCover());
      book.setOwner(user);
      book.setDateCreate(book1.getDateCreate());
      book.setState(book1.getState());

      booksRepository.save(book);
    }
    return from(book);
  }


  @Override
  public BooksShortDto getBooks(Long userId) {
    List<Book> books;
    if (userId == null) {
      books = booksRepository.findAll(SORT_BY_DATA_CREATED_DESC);
    } else {
      if (!securityService.isUserPermission(userId)) {
        throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
      } else {
        User user = getUserOrElseThrow(userId);
        books = booksRepository.findAllByOwner(user, SORT_BY_DATA_CREATED_DESC);
      }
    }
    return BooksShortDto.from(BookShortDto.from(books));
  }

  @Override
  public BookDto getBookDetail(Long bookId) {
    Book book = getBookOrElseThrow(bookId);
    BookDto result = from(book);
    result.setLocation(booksRepository.findLocationBook(bookId));
    result.setQueueSize(waitLinesRepository.countByBook_BookId(bookId));
    return result;
  }

  public Category getCategoryOrElseThrow(Long categoryId) {
    Category category = categoriesRepository.findById(categoryId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Category with id <" + categoryId + "> not found"));
    return category;
  }

  public Book getBookOrElseThrow(Long bookId) {
    return booksRepository.findById(bookId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Book with id < " + bookId + " > not found"));
  }

  public User getUserOrElseThrow(Long userId) {
    User user = usersRepository.findById(userId)
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "User with id <" + userId + "> not found"));
    return user;
  }

  @Override
  public WaitLinePlaceDto addBookToUserBooks(WaitLineRequestDto waitLineRequestDto) {
    User user = getUserOrElseThrow(waitLineRequestDto.getUserId());
    Book book = getBookOrElseThrow(waitLineRequestDto.getBookId());

    if (Objects.equals(book.getOwner().getUserId(), user.getUserId())) {
      throw new RestException(HttpStatus.FORBIDDEN, "That user already have that book");
    }

    List<WaitLine> usersInLine = waitLinesRepository.findAllByBook(book);
    for (WaitLine waitline : usersInLine) {
      if (Objects.equals(waitline.getUser().getUserId(), user.getUserId())) {
        throw new RestException(HttpStatus.FORBIDDEN, "User have already booked that book");
      }
    }

    WaitLine waitLine = WaitLine.builder()
        .book(book)
        .user(user)
        .dateCreate(LocalDate.from(LocalDateTime.now()))
        .build();

    waitLinesRepository.save(waitLine);

    return WaitLinePlaceDto.from(waitLine, usersInLine.size() + 1);
  }


  @Override
  public BooksShortDto getHistory(Long userId) {
    User user = getUserOrElseThrow(userId);

    List<Book> books = new ArrayList<>();

    if (!securityService.isUserPermission(userId)) {
      throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
    }

    List<History> histories = historyRepository.findAllBookByUser(user, SORT_BY_ID_DESC);
    for (History history : histories) {
      books.add(history.getBook());
    }
    return BooksShortDto.from(BookShortDto.from(books));
  }

  @Override
  public BooksShortDto getWaitList(Long userId) {
    User user = getUserOrElseThrow(userId);

    if (!securityService.isUserPermission(userId)) {
      throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
    }

    List<Book> booksFromWaitLine = new ArrayList<>();
    List<WaitLine> waitLines = waitLinesRepository.findAllByUser(user);
    for (WaitLine waitLine : waitLines) {
      booksFromWaitLine.add(waitLine.getBook());
    }

    //this part will be useless, after finished task 'send book to another user'
    List<Book> booksFromHistory = new ArrayList<>();
    List<History> histories = historyRepository.findAllBookByUser(user, SORT_BY_ID_DESC);
    for (History history : histories) {
      booksFromHistory.add(history.getBook());
    }

    List<Book> books = booksFromWaitLine.stream()
            .filter(book -> !booksFromHistory.contains(book))
            .collect(Collectors.toList());

    return BooksShortDto.from(BookShortDto.from(books));
  }

  @Override
  public FilterDTO getFilter() {
    return FilterDTO.builder()
        .language(booksRepository.findLanguageForFilter())
        .category(booksRepository.findCategoryForFilter())
        .location(locationRepository.findCityPostalCodeForFilter())
        .build();
  }

  @Override
  public BooksShortDto getSendList(Long userId) {
    User user = getUserOrElseThrow(userId);

    if (!securityService.isUserPermission(userId)) {
      throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
    }

    //get all books from owner
    List<Book> booksFromOwner = booksRepository.findAllByOwner(user, SORT_BY_DATA_CREATED_DESC);

    List<WaitLine> waitLines = waitLinesRepository.findAll();
    List<Book> booksFromWaitLine = new ArrayList<>();

    //get all books from wait list
    for (WaitLine waitLine : waitLines) {
      booksFromWaitLine.add(waitLine.getBook());
    }

    //in result list, will be only same books from owner and wait list
    List<Book> resultBooks = new ArrayList<>(booksFromWaitLine);
    resultBooks.retainAll(booksFromOwner);

    //here we clear from doubles list of books
    Set<Book> uniqueBooks = new HashSet<>(resultBooks);
    resultBooks.clear();
    resultBooks.addAll(uniqueBooks);

    return BooksShortDto.from(BookShortDto.from(resultBooks));
  }

  @Override
  public WaitLineNextUserDto getInfoAboutNextReaderInLine(WaitLineRequestDto waitLineRequestDto) {
    User user = getUserOrElseThrow(waitLineRequestDto.getUserId());
    Book book = getBookOrElseThrow(waitLineRequestDto.getBookId());

    if (!securityService.isUserPermission(user.getUserId())) {
      throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
    }

    //method used id, as a comparative parameter, for update more precisely needs to use LocalDateTime
    User nextUser = waitLinesRepository.findTopByUser(book);

//    WaitLine firstSignInList = Collections.min(books, Comparator.comparing(WaitLine::getLineId)); //здесь ошибка!

    return WaitLineNextUserDto.from(nextUser);
  }
}

