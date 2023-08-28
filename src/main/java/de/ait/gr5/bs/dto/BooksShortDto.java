package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "Short information about book")
public class BooksShortDto {

  private List<BookShortDto> books;
  public Integer count;

  public static BooksShortDto from(List<BookShortDto> books) {
    return BooksShortDto.builder()
        .books(books)
        .count(books.size())
        .build();
  }
}
