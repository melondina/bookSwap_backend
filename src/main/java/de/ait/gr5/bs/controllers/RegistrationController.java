package de.ait.gr5.bs.controllers;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class RegistrationController implements RegistrationApi {

    RegistrationService registrationService;

    @Override
    public ResponseEntity<UserDto> register(RegisterDto registerData) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationService.register(registerData));
    }
}
