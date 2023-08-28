package de.ait.gr5.bs.exceptions;

import de.ait.gr5.bs.handler.RestException;
import org.springframework.http.HttpStatus;

public class ForbiddenUpdateUserOperationException extends RestException {

    public ForbiddenUpdateUserOperationException(String field, String newValue) {
        super(HttpStatus.FORBIDDEN, "Cannot set <" + field + "> as <" + newValue + ">");
    }

}
