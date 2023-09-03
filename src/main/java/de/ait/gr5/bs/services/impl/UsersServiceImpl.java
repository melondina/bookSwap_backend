package de.ait.gr5.bs.services.impl;


import de.ait.gr5.bs.dto.UpdateUserDto;
import de.ait.gr5.bs.dto.UserDto;
import de.ait.gr5.bs.exceptions.CityNotFoundException;
import de.ait.gr5.bs.exceptions.ForbiddenUpdateUserOperationException;
import de.ait.gr5.bs.exceptions.NotFoundException;
import de.ait.gr5.bs.handler.RestException;
import de.ait.gr5.bs.models.City;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.repositories.UsersRepository;
import de.ait.gr5.bs.security.details.SecurityService;
import de.ait.gr5.bs.services.LocationService;
import de.ait.gr5.bs.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static de.ait.gr5.bs.dto.UserDto.from;


@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final SecurityService securityService;

    private final LocationService locationService;

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User", userId));
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUser) {

        String postalCode = updateUser.getPostalCode().toString();
        City city = locationService.getOrCreatedCity(postalCode);

        if (city == null) {
            throw new RestException(HttpStatus.BAD_REQUEST, "City with postal code " + postalCode + " not found");
        }

        if (updateUser.getNewRole().equals("ADMIN")) {
            throw new ForbiddenUpdateUserOperationException("role", "ADMIN");
        }

        if (!securityService.isUserPermission(userId)) {
            throw new RestException(HttpStatus.FORBIDDEN, "Not have permission");
        } else {
            User user = getUserOrThrow(userId);

            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setCity(city);
            user.setRole(User.Role.valueOf(updateUser.getNewRole()));

            if (user.getFirstName() != null && user.getLastName() != null && user.getCity() != null
            ) {
                user.setState(User.State.valueOf("CONFIRMED"));
            }

            usersRepository.save(user);

            return from(user);
        }
    }

    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

}
