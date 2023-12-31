package de.ait.gr5.bs.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import de.ait.gr5.bs.models.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Book")
public class BookDto {

  @Schema(description = "Book ID", example = "1")
  private Long bookId;

  @Schema(description = "Book title", example = "Braiding Sweetgrass")
  private String title;

  @Schema(description = "Author(s) of the book", example = "Robin Wall Kimmerer")
  private String author;

  @Schema(description = "Description (optional field)", example = "Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
  private String description;

  @Schema(description = "Books categories", example = "Esse")
  private String category;

  @Schema(description = "Book language", example = "English")
  private String language;

  @Schema(description = "Number of pages in the book", example = "408")
  private String pages;

  @Schema(description = "Publisher date of the book, format YYYY", example = "2015")
  private String publisherDate;

  @Schema(description = "Book cover photo", example = "https://bilder.buecher.de/produkte/66/66249/66249530n.jpg")
  private String cover;

  @Schema(description = "Owner ID", example = "1")
  private String owner;

  @Schema(description = "Location of the book", example = "13599 Berlin")
  private String location;

  @Schema(description = "Number of readers in the queue", example = "3")
  private String queueSize;


  public static BookDto from(Book book) {
    return BookDto.builder()
        .bookId(book.getBookId())
        .title(book.getTitle())
        .author(book.getAuthor())
        .description(book.getDescription())
        .category(book.getCategory().getTitleCategory())
        .language(book.getLanguage().getTitle())
        .pages(book.getPages().toString())
        .cover(book.getCover())
        .owner(book.getOwner().getUserId().toString())
        .publisherDate(book.getPublisherDate())
        .build();

  }

}
