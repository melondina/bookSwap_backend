package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.LanguagesDto;
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

@RequestMapping("api/languages")
@Tags(value =
@Tag(name = "Languages"))
public interface LanguagesApi {

  @Operation(summary = "List of languages", description = "List of language titles")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of language titles",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = LanguagesDto.class))
          })
  })
  @GetMapping()
  ResponseEntity<LanguagesDto> getLanguages();
}
