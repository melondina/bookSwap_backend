package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Short information about all user books")
public class AllUserBooksDto {

  @Schema(description = "List of books and numbers of books in Library")
  private BooksShortDto booksInLibrary;

  @Schema(description = "List of books and numbers of books in History")
  private BooksShortDto booksInHistory;

  @Schema(description = "List of books and numbers of books in Wait line")
  private BooksShortDto booksInWaitLine;

  @Schema(description = "List of books and numbers of books for Sending")
  private BooksShortDto booksToSend;

}
