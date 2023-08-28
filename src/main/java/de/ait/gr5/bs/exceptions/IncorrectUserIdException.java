package de.ait.gr5.bs.exceptions;

import de.ait.gr5.bs.handler.RestException;
import org.springframework.http.HttpStatus;

public class IncorrectUserIdException extends RestException {

    public IncorrectUserIdException(Long incorrectId) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, "Id of user <" + incorrectId + "> is incorrect.");
    }
}
