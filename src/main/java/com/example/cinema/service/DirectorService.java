package com.example.cinema.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.cinema.dao.DirectorDao;
import com.example.cinema.model.Director;

@Service
public class DirectorService {

	private final DirectorDao directorDao;
	
	public DirectorService(@Qualifier("director-postgres")DirectorDao directorDao) {
		this.directorDao = directorDao;
	}
	
	public int insertDirector(Director director) {
		return directorDao.insertDirector(director);
	}
	
	public List<Director> getAllDirectors() {
		return directorDao.selectAllDirectors();
	}
	
	public Optional<Director> getDirectorById(int id) {
		return directorDao.selectDirectorById(id);
	}
	
	public int deleteDirector(int id) {
		return directorDao.deleteDirectorById(id);
	}
	
	public int updateDirector(int id, Director director) {
		return directorDao.updateDirectorById(id, director);
	}
	
	public Optional<Director> getDirectorByIdWithFilms(int id) {
		return directorDao.selectDirectorByIdWithFilms(id);
	}
	
	public Optional<Director> getDirectorByIdWithFilmsReleasedAfter(int id, LocalDate minReleaseDate) {
		return directorDao.selectDirectorByIdWithFilmsReleasedAfter(id, minReleaseDate);
	}
	
	public List<Director> getDirectorsWithFilmsReleasedAfter(LocalDate minReleaseDate) {
		return directorDao.selectDirectorsWithFilmsReleasedAfter(minReleaseDate);
	}
	
	public List<Director> getAllDirectorsWithFilms() {
		return directorDao.selectAllDirectorsWithFilms();
	}
}
