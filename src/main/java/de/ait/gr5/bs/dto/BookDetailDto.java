package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Book")
public class BookDetailDto {

  @Schema(description = "Book ID", example = "1")
  private Long bookId;
  // https://bookshop.org/p/books/braiding-sweetgrass-robin-wall-kimmerer/16712606?ean=9781571313560
  @Schema(description = "Book title", example = "Braiding Sweetgrass")
  private String title;

  @Schema(description = "Author(s) of the book", example = "Robin Wall Kimmerer")
  private String author;

  @Schema(description = "Description (optional field)", example = "Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
  private String description;

  @Schema(description = "Books categories", example = "Ecology, Essays, Cultural & Social")
  private String category;

  @Schema(description = "Book language", example = "English")
  private String language;

  @Schema(description = "Number of pages in the book", example = "408")
  private Integer pages;

  @Schema(description = "Publisher date of the book, format YYYY-MM-DD", example = "2015-04-11")
  private String publisherDate;

  @Schema(description = "Book cover photo", example = "f:/db/1.jpg")
  private String cover;

//  @Schema(description = "Book owner", example = "User1")
//  private UserDto owner;

  @Schema(description = "Date create of the book in the app, format YYYY-MM-DD", example = "2023-08-23")
  private String dateCreate;

  @Schema(description = "State of the book - AVAILABLE, DELETED", example = "AVAILABLE")
  private String state;

  public static BookDetailDto from(Book book) {
    BookDetailDto result = BookDetailDto.builder()
        .bookId(book.getBookId())
        .title(book.getTitle())
        .author(book.getAuthor())
        .description(book.getDescription())
        .category(book.getCategory().getTitle())
        .language(book.getLanguage())
        .pages(book.getPages())
        .cover(book.getCover())
        .state(book.getState().name())
        .build();
    if (book.getPublisherDate() != null) {
      result.setPublisherDate(book.getPublisherDate().toString());
    }
    if (book.getDateCreate() != null) {
      result.setDateCreate(book.getDateCreate().toString());
    }
    return result;
  }

  public static List<BookDetailDto> from(Collection<Book> books) {
    return books.stream()
        .map(BookDetailDto::from)
        .collect(Collectors.toList());
  }
}
