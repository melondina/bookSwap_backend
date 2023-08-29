package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Wait Line")
public class WaitLinePlaceDto {

  @Schema(description = "Wait Line ID", example = "1")
  private Long lineId;

  //todo add documentation
  private BookDto bookDto;

  //todo add documentation
  private UserDto userDto;

  @Schema(description = "The place of user in line for that book", example = "2")
  private Integer numberUserInLine;
}
