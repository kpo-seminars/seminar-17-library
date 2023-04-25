package ru.hse.seminar17library.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class BookAlreadyUsedException extends ResponseStatusException {
    public BookAlreadyUsedException() {
        super(HttpStatusCode.valueOf(500), "Book already is used");
    }
}
