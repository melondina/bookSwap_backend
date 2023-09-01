package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.WaitLine;
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

  @Schema(description = "Book Id", example = "1")
  private String bookId;

  @Schema(description = "User Id", example = "1")
  private String userId;

  @Schema(description = "The place of user in line for that book", example = "2")
  private Integer numberUserInLine;

  public static WaitLinePlaceDto from(WaitLine waitLine, Integer numberUserInLine) {
    return WaitLinePlaceDto.builder()
            .lineId(waitLine.getLineId())
            .bookId(String.valueOf(waitLine.getBook().getBookId()))
            .userId(String.valueOf(waitLine.getUser().getUserId()))
            .numberUserInLine(numberUserInLine)
            .build();
  }
}
