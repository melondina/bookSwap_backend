package de.ait.gr5.bs.services;

import de.ait.gr5.bs.dto.RegistrationDto;
import de.ait.gr5.bs.dto.UserDto;


public interface RegistrationService {
    UserDto registration(RegistrationDto registerData);
}
