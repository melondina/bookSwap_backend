package de.ait.gr5.bs.services.impl;


import de.ait.gr5.bs.dto.UpdateUserDto;
import de.ait.gr5.bs.dto.UserDto;
import de.ait.gr5.bs.exceptions.ForbiddenUpdateUserOperationException;
import de.ait.gr5.bs.exceptions.NotFoundException;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.repositories.UsersRepository;
import de.ait.gr5.bs.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static de.ait.gr5.bs.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User", userId));
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUser) {

        User user = getUserOrThrow(userId);

        if (updateUser.getNewRole().equals("ADMIN")) {
            throw new ForbiddenUpdateUserOperationException("role", "ADMIN");
        }

        if (updateUser.getNewState().equals("DELETED")) {
            throw new ForbiddenUpdateUserOperationException("state", "DELETED");
        }

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
      //  country city
        user.setPostalCode(updateUser.getPostalCode());
        user.setState(User.State.valueOf(updateUser.getNewState()));
        user.setRole(User.Role.valueOf(updateUser.getNewRole()));

        usersRepository.save(user);

        return from(user);
    }

}
