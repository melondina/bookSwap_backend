package de.ait.gr5.bs.controllers;


import de.ait.gr5.bs.controllers.api.UsersApi;
import de.ait.gr5.bs.dto.UpdateUserDto;
import de.ait.gr5.bs.dto.UserDto;
import de.ait.gr5.bs.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UsersController implements UsersApi {

    private final UsersService usersService;


    @Override
    public ResponseEntity<UserDto> updateUser(Long userId, UpdateUserDto updateUser) {
        return ResponseEntity
                .ok(usersService.updateUser(userId, updateUser));
    }

}

