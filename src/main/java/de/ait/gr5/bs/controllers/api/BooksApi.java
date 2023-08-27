package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.BookDto;
import de.ait.gr5.bs.dto.BookNewDto;
import de.ait.gr5.bs.dto.BookUpdateDto;
import de.ait.gr5.bs.dto.StandardResponseDto;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
          })
  })
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<BookDto> addBook(@Parameter(required = true, description = "New book") @RequestBody @Valid BookNewDto newBook);

  @Operation(summary = "Book update", description = "Available to registered user and administrator")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "404", description = "Book not found",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = StandardResponseDto.class))
          }),
      @ApiResponse(responseCode = "400", description = "Validation error",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
          }),
      @ApiResponse(responseCode = "200", description = "Book update",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BookDto.class))
          })
  })
  @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
  @PutMapping("/{book-id}")
  ResponseEntity<BookDto> updateBook(@Parameter(required = true, description = "Book ID", example = "1")
                                     @PathVariable("book-id") Long bookId,
                                     @RequestBody @Valid BookUpdateDto updateBook);


}
