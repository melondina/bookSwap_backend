package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@Schema(description = "Short information about book")
public class BookShortDto {
  @Schema(description = "Book ID", example = "1")
  private Long bookId;
  @Schema(description = "Book cover photo", example = "https://images2.medimops.eu/product/be4f21/M01604591870-large.jpg")
  private String cover;
  @Schema(description = "Book title", example = "Think and Grow Rich")
  private String title;
  @Schema(description = "Books categories", example = "Detective")
  private String category;
  @Schema(description = "Book language", example = "English")
  private String language;
  @Schema(description = "Author(s) of the book", example = "Robin Wall Kimmerer")
  private String author;

  public static BookShortDto from(Book book) {
    return BookShortDto.builder()
        .bookId(book.getBookId())
        .cover(book.getCover())
        .title(book.getTitle())
        .category(book.getCategory().getTitle())
        .language(book.getLanguage().getTitle())
        .author(book.getAuthor())
        .build();
  }

  public static List<BookShortDto> from(List<Book> books) {
    return books.stream()
        .map(BookShortDto::from)
        .collect(Collectors.toList());

  }
}
