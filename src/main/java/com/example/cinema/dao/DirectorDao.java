package com.example.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.cinema.model.Director;

public interface DirectorDao {

	int insertDirector(Director director);
	
	List<Director> selectAllDirectors();
	
	Optional<Director> selectDirectorById(int id);
	
	int deleteDirectorById(int id);
	
	int updateDirectorById(int id, Director director);
	
	public Optional<Director> selectDirectorByIdWithFilms (int queryId);

	Optional<Director> selectDirectorByIdWithFilmsReleasedAfter(int id, LocalDate minReleaseDate);

	List<Director> selectDirectorsWithFilmsReleasedAfter(LocalDate minReleaseDate);

	List<Director> selectAllDirectorsWithFilms();
}
