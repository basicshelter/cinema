package com.example.cinema.dao;

import java.util.List;
import java.util.Optional;

import com.example.cinema.model.Film;

public interface FilmDao {

	int insertFilm(Film film);
	
	List<Film> selectAllFilms();
	
	Optional<Film> selectFilmById(int id);
	
	int deleteFilmById(int id);
	
	int updateFilmById(int id, Film film);
}
