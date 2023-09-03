package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.CategoriesDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/categories")
@Tags(value =
@Tag(name = "Categories"))
public interface CategoriesApi {

  @Operation(summary = "List of categories", description = "List of category titles")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of category titles",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = CategoriesDto.class))
          })
  })
  @GetMapping()
  ResponseEntity<CategoriesDto> getCategories();
}
