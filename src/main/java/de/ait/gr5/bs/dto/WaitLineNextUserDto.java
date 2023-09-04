package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.City;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.models.WaitLine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Schema(description = "Data about next user")
@Builder
public class WaitLineNextUserDto {

  @Schema(description = "User ID", example = "1")
  private Long userId;

  @Schema(description = "Users firstname", example = "Peter")
  private String firstName;

  @Schema(description = "Users lastname", example = "Wolf")
  private String lastName;

  @Schema(description = "Users email", example = "first.user@gmail.com")
  private String email;

  public static WaitLineNextUserDto from(User nextUser) {
    return WaitLineNextUserDto.builder()
            .userId(nextUser.getUserId())
            .firstName(nextUser.getFirstName())
            .lastName(nextUser.getLastName())
            .email(nextUser.getEmail())
            .build();
  }
}
