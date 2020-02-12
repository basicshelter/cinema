package com.example.cinema.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.cinema.model.Director;

@Repository("fakeDao")
public class FakeDirectorDataAccessService implements DirectorDao{

	private static List<Director> DB = new ArrayList<>();
	
	
	@Override
	public int insertDirector(Director director) {
		DB.add(new Director (director.getFirstName(), director.getLastName(), director.getBirthDate()));
		return 1;
	}

	@Override
	public List<Director> selectAllDirectors() {
		return DB;
	}

	@Override
	public Optional<Director> selectDirectorById(int id) {
		return DB.stream().filter(director -> director.getId() == id).findFirst();
	}

	@Override
	public int deleteDirectorById(int id) {
		Optional<Director> director = selectDirectorById(id);
		if (director.isEmpty()) {
			return 0;
		}
		DB.remove(director.get());
		return 1;
	}

	@Override
	public int updateDirectorById(int id, Director director) {
//		return selectDirectorById(id).map(d -> {
//			int index = DB.indexOf(d);
//			if (index >= 0) {
//				DB.set(index, new Director(id, director.getFirstName(), director.getLastName(), director.getBirthDate()));
//				return 1;
//			}
			return 0;
//		}).orElse(0);
	}

	@Override
	public Optional<Director> selectDirectorByIdWithFilms(int queryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Director> selectDirectorByIdWithFilmsReleasedAfter(int id, LocalDate minReleaseDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Director> selectDirectorsWithFilmsReleasedAfter(LocalDate minReleaseDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Director> selectAllDirectorsWithFilms() {
		// TODO Auto-generated method stub
		return null;
	}
}
