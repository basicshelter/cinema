package com.example.cinema.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DirectorFilmsNotFoundException extends RuntimeException {
	public DirectorFilmsNotFoundException(int id) {
        super("Films by director id: " + id + " weren\'t found");
    }
	public DirectorFilmsNotFoundException(int id, LocalDate releaseDate) {
        super("Director with id: " + id + " doesen\'t have films after " + releaseDate + " or doesen\'t exist.");
    }

}
