package de.ait.gr5.bs.exceptions;

import de.ait.gr5.bs.handler.RestException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends RestException {

    public NotFoundException(String entity, Long id) {
        super(HttpStatus.NOT_FOUND, entity + " with id <" + id + "> not found.");
    }
}
