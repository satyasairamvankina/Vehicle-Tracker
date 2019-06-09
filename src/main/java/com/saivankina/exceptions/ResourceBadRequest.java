package com.saivankina.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequest extends RuntimeException {
    public ResourceBadRequest(String s) {
        super(s);
    }
}
