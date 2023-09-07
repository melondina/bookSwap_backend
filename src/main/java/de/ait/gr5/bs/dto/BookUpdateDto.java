package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(description = "Update book")
public class BookUpdateDto {

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
  private Long categoryId;

  @Schema(description = "Book language", example = "1")
  @NotNull
  private Long languageId;

  @Schema(description = "Number of pages in the book", example = "408")
  @NotNull
  private Integer pages;

  @Schema(description = "Publisher date of the book, format YYYY", example = "2015")
  @NotNull
  @NotBlank
  private String publisherDate;

  @Schema(description = "Book cover photo", example = "https://bilder.buecher.de/produkte/66/66249/66249530n.jpg")
  @NotNull
  @NotBlank
  private String cover;

  @Schema(description = "Books owner ID", example = "1")
  @NotNull
  private Long owner;

}
