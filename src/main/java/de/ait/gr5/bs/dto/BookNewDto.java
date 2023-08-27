package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@Schema(description = "New book")
public class BookNewDto {

  // https://bookshop.org/p/books/braiding-sweetgrass-robin-wall-kimmerer/16712606?ean=9781571313560
  @Schema(description = "Book title", example = "Braiding Sweetgrass")
  @NotNull
  @NotBlank
  private String title;

  @Schema(description = "Author(s) of the book", example = "Robin Wall Kimmerer")
  @NotNull
  @NotBlank
  private String author;

  @Schema(description = "Description (optional field)", example = "Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
  @NotNull
  @NotBlank
  private String description;

  @Schema(description = "Books categories ID", example = "1")
  @NotNull
  @NotBlank
  private Long categoryId;

  @Schema(description = "Book language", example = "English")
  @NotNull
  @NotBlank
  private String language;

  @Schema(description = "Number of pages in the book", example = "408")
  @NotNull
  @NotBlank
  private Integer pages;

  @Schema(description = "Publisher date of the book, format YYYY-MM-DD", example = "2015-04-11")
  @NotNull
  @NotBlank
  private String publisherDate;

  @Schema(description = "Book cover photo", example = "f:/book-db/1.jpg")
  private String cover;

  @Schema(description = "Book owner ID", example = "1")
  @NotNull
  @NotBlank
  private Long owner;

}
