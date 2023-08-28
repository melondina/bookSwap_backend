package de.ait.gr5.bs.dto;

import de.ait.gr5.bs.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "User of system")
public class UserDto {

    @Schema(description = "User ID", example = "1")
    private Long id;

    @Schema(description = "Users email", example = "first.user@gmail.com" )
    private String email;

    @Schema(description = "Users role - ADMIN - administrator, USER - user", example = "ADMIN")
    private String role;

    @Schema(description = "Users state - NOT_CONFIRMED, CONFIRMED, DELETED", example = "CONFIRMED")
    private String state;

    @Schema(description = "Users firstname", example = "Peter")
    private String firstName;

    @Schema(description = "Users lastname", example = "Wolf")
    private String lastName;

    // country, city?
    // private String country;

    @Schema(description = "Users postal code", example = "23654")
    private Integer postalCode;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getUserId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .state(user.getState().name())
                .postalCode(user.getPostalCode())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static List<UserDto> from(Collection<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

}
