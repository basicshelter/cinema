package com.example.cinema.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends RuntimeException {
	public FilmNotFoundException(int id) {
        super("Film id not found : " + id);
    }

}
