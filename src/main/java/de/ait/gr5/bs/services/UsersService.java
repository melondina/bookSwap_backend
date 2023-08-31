package de.ait.gr5.bs.services;


import de.ait.gr5.bs.dto.UpdateUserDto;
import de.ait.gr5.bs.dto.UserDto;

public interface UsersService {

    UserDto updateUser(Long userId, UpdateUserDto updateUser);

    UserDto getUser(Long userId);
}
