package ru.hse.seminar17library.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BookIsNotUsedByUserException extends ResponseStatusException {
    public BookIsNotUsedByUserException() {
        super(HttpStatusCode.valueOf(500), "Book is not used by user");
    }
}
