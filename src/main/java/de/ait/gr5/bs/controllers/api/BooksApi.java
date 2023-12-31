package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.*;
import de.ait.gr5.bs.handler.RestException;
import de.ait.gr5.bs.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/books")
@Tags(value =
@Tag(name = "Books"))
public interface BooksApi {

  @Operation(summary = "Add a book", description = "Available to registered user and administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Book added",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))
          }),
      @ApiResponse(responseCode = "400", description = "Validation error",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Fill in full details about yourself in your profile",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User not authenticated",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<BookDto> addBook(@Parameter(required = true, description = "New book") @RequestBody @Valid BookNewDto newBook);


  @Operation(summary = "Book update", description = "Available to registered user and administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book update",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))
          }),
      @ApiResponse(responseCode = "400", description = "Validation error",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "Book not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User not authenticated",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @PutMapping("/{book-id}")
  ResponseEntity<BookDto> updateBook(@Parameter(required = true, description = "Book ID", example = "1")
                                     @PathVariable("book-id") Long bookId,
                                     @RequestBody @Valid BookUpdateDto updateBook);


  @Operation(summary = "List of books", description = "List of books with short information")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of books",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BooksShortDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @GetMapping()
  ResponseEntity<BooksShortDto> getBooks(UserFilterSearchDTO filterForSearch);


  @Operation(summary = "Get book from list to user books", description = "Available for authorised users")
  @ApiResponses(value = {

      @ApiResponse(responseCode = "200", description = "Books have been added to the wait list of user",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = WaitLinePlaceDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "Book or user not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "User already get book or stay in line for that book",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @PostMapping("/getting")
  ResponseEntity<WaitLinePlaceDto> addBookToUserBooks(
      @Parameter(required = true, description = "Book ID", example = "1")
      @RequestBody @Valid WaitLineRequestDto waitLineRequestDto);

  @Operation(summary = "Book detail", description = "Detailed information about the book")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "404", description = "Book not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "200", description = "Book detail",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))
          })
  })
  @GetMapping("/{book_id}/detail")
  ResponseEntity<BookDto> getBookDetail(@Parameter(required = true, description = "Book id", example = "2")
                                        @PathVariable(name = "book_id") Long bookId);


  @Operation(summary = "List of books read", description = "List of books read")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of books read",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BooksShortDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @GetMapping("/history/{user-id}")
  ResponseEntity<BooksShortDto> getHistory(@Parameter(required = true, description = "User ID", example = "1")
                                           @PathVariable("user-id") Long userId);


  @Operation(summary = "List of waiting books", description = "List of waiting books")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of waiting books",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BooksShortDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @GetMapping("/waiting/{user-id}")
  ResponseEntity<BooksShortDto> getWaitList(@Parameter(required = true, description = "User ID", example = "1")
                                            @PathVariable("user-id") Long userId);


  @Operation(summary = "List for filter")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List for filter",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = FilterDTO.class))
          })
  })
  @GetMapping("/filter")
  ResponseEntity<FilterDTO> getFilter();

  @Operation(summary = "List books to send", description = "List books to send")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List books to send",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BooksShortDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @GetMapping("/send/{user-id}")
  ResponseEntity<BooksShortDto> getSendList(@Parameter(required = true, description = "User ID", example = "1")
                                            @PathVariable("user-id") Long userId);

  @Operation(summary = "Data of next reader of particular book", description = "next reader of particular book")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book info",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BooksShortDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "User not found, Book not found, Book not in wait list",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @GetMapping("/send/to")
  ResponseEntity<WaitLineNextUserDto> getInfoAboutNextReaderInLine(
      @RequestBody @Valid WaitLineRequestDto waitLineRequestDto);

  @Operation(summary = "Book was send to next user in line", description = "Book to next user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Book was send",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BooksShortDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "User not found, Book not found, Book is not in the wait list",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = RestException.class))
          })
  })
  @PostMapping("/send/to")
  ResponseEntity<AllUserBooksDto> sendBookToNextUser(
      @RequestBody @Valid WaitLineRequestDto waitLineRequestDto);

  @Operation(summary = "All info about user books", description = "All info about user books")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List was send",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = AllUserBooksDto.class))
          }),
      @ApiResponse(responseCode = "403", description = "Not have permission",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "User not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          })
  })
  @GetMapping("/all/{user-id}")
  ResponseEntity<AllUserBooksDto> getAllUserBooksInfo(@Parameter(required = true, description = "User ID", example = "1")
                                                      @PathVariable("user-id") Long userId);

  @Operation(summary = "Remove book from list to user books")
  @ApiResponses(value = {

      @ApiResponse(responseCode = "200", description = "The book has been removed from the user's waiting list",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = WaitLinePlaceDto.class))
          }),
      @ApiResponse(responseCode = "401", description = "User unauthorized",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "404", description = "Book or user not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
  })
  @DeleteMapping("/remove")
  ResponseEntity<AllUserBooksDto> removeBookFromUserBooks(
      @RequestBody @Valid WaitLineRequestDto waitLineRequestDto);

}
