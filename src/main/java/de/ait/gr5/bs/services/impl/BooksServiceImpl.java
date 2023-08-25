package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.BookDetailDto;
import de.ait.gr5.bs.dto.BookNewDto;
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

import static de.ait.gr5.bs.dto.BookDetailDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

  BooksRepository booksRepository;

  UsersRepository usersRepository;

  CategoriesRepository categoriesRepository;

  @Override
  public BookDetailDto addBook(BookNewDto newBook) {
    User user = usersRepository.findById(newBook.getOwner())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "User with id<" + newBook.getOwner() + ">not found"));

    Category category = categoriesRepository.findById(newBook.getCategoryId())
        .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND,
            "Category with id<" + newBook.getCategoryId() + ">not found"));

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

}
