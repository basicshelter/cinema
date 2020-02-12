package com.example.cinema.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public void insertFilm(@RequestBody Film film) {
		filmService.insertFilm(film);
	}
	
	@GetMapping
	public List<Film> getAllFilms() {
		return filmService.getAllFilms();
	}
	
	@GetMapping(path = "{id}")
	public Film getFilmById(@PathVariable("id") int id) {
		return filmService.getFilmById(id).orElse(null);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteFilmById(@PathVariable("id") int id) {
		filmService.deleteFilm(id);
	}
	
	@PutMapping(path = "{id}")
	public void updateFilm(@PathVariable("id") int id, @Valid @NotNull @RequestBody Film film) {
		filmService.updateFilm(id, film);
	}
}
