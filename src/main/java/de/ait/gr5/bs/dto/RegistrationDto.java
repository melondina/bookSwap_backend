package de.ait.gr5.bs.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;


@Data
public class RegistrationDto {

    @Schema(description = "Users email", example = "first.user@gmail.com")
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Schema(description = "Users password", example = "Qwerty007!")
    @NotBlank
    @Size(min = 7, max = 1000)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Weak password")
    private String password;
}
