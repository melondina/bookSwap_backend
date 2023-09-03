package de.ait.gr5.bs.exceptions;

import de.ait.gr5.bs.handler.RestException;
import org.springframework.http.HttpStatus;

public class CityNotFoundException extends RestException {

    public CityNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
