package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(description = "New book in WaitLine")
public class WaitLineRequestDto {

  @Schema(description = "Books ID", example = "1")
  @NotNull
  private Long bookId;

  @Schema(description = "User ID, that what want to have book", example = "1")
  @NotNull
  private Long userId;
}
