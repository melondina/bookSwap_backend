package de.ait.gr5.bs.services.impl;

import de.ait.gr5.bs.dto.RegistrationDto;
import de.ait.gr5.bs.dto.UserDto;
import de.ait.gr5.bs.models.User;
import de.ait.gr5.bs.repositories.UsersRepository;
import de.ait.gr5.bs.services.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static de.ait.gr5.bs.dto.UserDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    UsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto registration(RegistrationDto registerData) {
        User user = User.builder()
                .email(registerData.getEmail())
                .password(passwordEncoder.encode(registerData.getPassword()))
                .role(User.Role.USER)
                .state(User.State.CONFIRMED)
                .build();

        usersRepository.save(user);

        return from(user);
    }
}
