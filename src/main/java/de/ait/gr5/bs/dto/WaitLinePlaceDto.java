package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.WaitLine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

  public static WaitLinePlaceDto from(List<WaitLine> checkTheNumbers, WaitLine waitLine) {
    return WaitLinePlaceDto.builder()
            .lineId(waitLine.getLineId())
            .bookDto(BookDto.from(waitLine.getBook()))
            .userDto(UserDto.from(waitLine.getUser()))
            .numberUserInLine(checkTheNumbers.size())
            .build();
  }
}
