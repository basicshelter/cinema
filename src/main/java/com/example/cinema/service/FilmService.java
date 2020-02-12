package com.example.cinema.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.cinema.dao.FilmDao;
import com.example.cinema.model.Film;

@Service
public class FilmService {
	private final FilmDao filmDao;
	
	public FilmService(@Qualifier("film-postgres")FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	public int insertFilm(Film film) {
		return filmDao.insertFilm(film);
	}
	
	public List<Film> getAllFilms() {
		return filmDao.selectAllFilms();
	}

	public Optional<Film> getFilmById(int id) {
		return filmDao.selectFilmById(id);
	}
	
	public int deleteFilm(int id) {
		return filmDao.deleteFilmById(id);
	}
	
	public int updateFilm(int id, Film film) {
		return filmDao.updateFilmById(id, film);
	}
}
