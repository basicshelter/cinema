package com.example.cinema.api;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.cinema.exception.CinemaErrorDetails;
import com.example.cinema.exception.FilmNotFoundException;
import com.example.cinema.model.Film;
import com.example.cinema.service.FilmService;

@RequestMapping("/api/film")
@RestController
public class FilmController {
	
	private final FilmService filmService;
	
	@Autowired
	public FilmController(FilmService filmService) {
		this.filmService = filmService;
	}
	
	@PostMapping
	public void insertFilm(@RequestBody @Valid @NotNull Film film) {
		filmService.insertFilm(film);
	}
	
	@GetMapping
	public List<Film> getAllFilms() {
		return filmService.getAllFilms();
	}
	
	@GetMapping(path = "{id}")
	public Film getFilmById(@PathVariable("id") int id) {
		return filmService.getFilmById(id).orElseThrow(() -> new FilmNotFoundException(id));
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteFilmById(@PathVariable("id") int id) {
		filmService.deleteFilm(id);
	}
	
	@PutMapping(path = "{id}")
	public void updateFilm(@PathVariable("id") int id, @RequestBody @Valid @NotNull Film film) {
		filmService.updateFilm(id, film);
	}
	
	@ExceptionHandler(FilmNotFoundException.class)
	public ResponseEntity<CinemaErrorDetails> handleDirectorFilmsNotFound(Exception ex, WebRequest request) {

		CinemaErrorDetails error = new CinemaErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
