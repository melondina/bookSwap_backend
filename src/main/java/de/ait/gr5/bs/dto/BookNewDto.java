package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "New book")
public class BookNewDto {

  // https://bookshop.org/p/books/braiding-sweetgrass-robin-wall-kimmerer/16712606?ean=9781571313560
  @Schema(description = "Book title", example = "Braiding Sweetgrass")
  private String title;

  @Schema(description = "Author(s) of the book", example = "Robin Wall Kimmerer")
  private String author;

  @Schema(description = "Description (optional field)", example = "Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
  private String description;

  @Schema(description = "Books categories", example = "Ecology, Essays, Cultural & Social")
  private Long categoryId;

  @Schema(description = "Book language", example = "English")
  private String language;

  @Schema(description = "Number of pages in the book", example = "408")
  private Integer pages;

  @Schema(description = "Publisher date of the book, format YYYY-MM-DD", example = "2015-04-11")
  private String publisherDate;

  @Schema(description = "Book cover photo", example = "f:/db/1.jpg")
  private String cover;

  @Schema(description = "Book owner", example = "User1")
  private Long owner;

  @Schema(description = "Date create of the book in the app, format YYYY-MM-DD", example = "2023-08-23")
  private String dateCreate;

}
