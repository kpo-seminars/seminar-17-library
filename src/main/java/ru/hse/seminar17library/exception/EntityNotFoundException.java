package ru.hse.seminar17library.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class EntityNotFoundException extends ResponseStatusException {
    public EntityNotFoundException() {
        super(HttpStatusCode.valueOf(404), "Entity not found");
    }
}
