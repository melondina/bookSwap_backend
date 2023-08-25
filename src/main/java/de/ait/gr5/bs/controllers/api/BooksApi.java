package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.BookDetailDto;
import de.ait.gr5.bs.dto.BookNewDto;
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
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("api/books")
@Tags(value =
@Tag(name = "Books"))
public interface BooksApi {

  @Operation(summary = "Добавление книги", description = "Доступно зарегистрированному пользователю и администратору")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Книга добавлена",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = BookDetailDto.class))
          }),
      @ApiResponse(responseCode = "400", description = "Ошибка валидации",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
          })
  })
  //@PreAuthorize("hasAnyAuthority('USER','ADMIN')") kogda podkluchu security
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  ResponseEntity<BookDetailDto> addBook(@Parameter(required = true, description = "Book") @RequestBody @Valid BookNewDto newBook);

}
