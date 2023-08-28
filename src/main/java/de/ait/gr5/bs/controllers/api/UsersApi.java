package de.ait.gr5.bs.controllers.api;

import de.ait.gr5.bs.dto.ErrorDto;
import de.ait.gr5.bs.dto.UpdateUserDto;
import de.ait.gr5.bs.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tags(value = {
        @Tag(name = "Users")
})
@RequestMapping("/api/users")
public interface UsersApi {

    @Operation(summary = "Update user", description = "Available to everyone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "User is not found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    }),
            @ApiResponse(responseCode = "403", description = "Can't be admin",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class))
                    }),
            @ApiResponse(responseCode = "200", description = "Updated user",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    })
    })
    @PutMapping("/{user-id}")
    ResponseEntity<UserDto> updateUser(@Parameter(required = true, description = "User ID", example = "2")
                                       @PathVariable("user-id") Long userId,
                                       @RequestBody UpdateUserDto updateUser);
}
