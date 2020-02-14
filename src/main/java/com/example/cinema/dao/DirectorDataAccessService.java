package com.example.cinema.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.cinema.exception.DirectorFilmsNotFoundException;
import com.example.cinema.exception.DirectorNotFoundException;
import com.example.cinema.model.Director;

@Repository("director-postgres")
public class DirectorDataAccessService implements DirectorDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DirectorDataAccessService(JdbcTemplate jdbcTemplate){
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int insertDirector(Director director) {
		final String sql = ""
				 + "INSERT INTO Director ("
				 + " first_name, "
				 + " last_name, "
				 + " birth_date) "
				 + "VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                director.getFirstName(),
                director.getLastName(),
                director.getBirthDate()
        );
	}

	@Override
	public List<Director> selectAllDirectors() {
		final String sql = "SELECT * FROM Director";
		return jdbcTemplate.query(sql, (resultSet, i) -> 
			 mapDirector(resultSet)
		);
	}

	@Override
	public Optional<Director> selectDirectorById(int queryId) {
		Director director = null;
		try {
			final String sql = "SELECT * FROM Director WHERE id = ?";
			director = jdbcTemplate.queryForObject(sql, new Object[] {queryId}, (resultSet, i) -> 
				mapDirector(resultSet)
			);
			return Optional.ofNullable(director);
		} catch (DataAccessException e) {
			throw new DirectorNotFoundException(queryId);
		}
	}

	@Override
	public int deleteDirectorById(int id) {
		try {
			final String sql = "DELETE FROM Director WHERE id = ?";
			return jdbcTemplate.update(sql, id);
		} catch (DataAccessException e) {
			throw new DirectorNotFoundException(id);
		}
	}

	@Override
	public int updateDirectorById(int id, Director director) {
		final String sql = ""
				 + "UPDATE Director "
				 + "SET first_name = ?, "
				 + "last_name = ?, "
				 + "birth_date = ? "
				 + "WHERE id = ?";
		return jdbcTemplate.update(
                sql,
                director.getFirstName(),
                director.getLastName(),
                director.getBirthDate(),
                id
        );
	}

	@Override
	public Optional<Director> selectDirectorByIdWithFilms (int id){
		final String sql = ""
				+ "SELECT director.id, director.first_name, director.last_name, director.birth_date, "
				+ "film.name, film.release_date, film.genre "
				+ "FROM Director "
				+ "JOIN Film ON director.id = film.director_id "
				+ "WHERE director.id = ?";
//		try {
			return jdbcTemplate.query(sql, new Object[] {id}, rs -> {
				Director director = null;
				while(rs.next()) {
					if(director == null) {
						director = mapDirector(rs);
					} 
					director.addFilm(FilmDataAccessService.mapFilmPart(rs));
				}
				return Optional.ofNullable(director);
			});
//		} catch (DataAccessException e) {
//			throw new DirectorNotFoundException(id);
//		}
		
	}
	
	@Override
	public Optional<Director> selectDirectorByIdWithFilmsReleasedAfter(int id, LocalDate minReleaseDate) {
		final String sql = ""
				+ "SELECT director.id, director.first_name, director.last_name, director.birth_date, "
				+ "film.name, film.release_date, film.genre "
				+ "FROM Director "
				+ "JOIN Film ON director.id = film.director_id "
				+ "WHERE director.id = ? "
				+ "AND release_date > ?";
		try {
			return jdbcTemplate.query(sql, new Object[] {id, minReleaseDate}, rs -> {
				Director director = null;
				while(rs.next()) {
					if(director == null) {
						director = mapDirector(rs);
					} 
					director.addFilm(FilmDataAccessService.mapFilmPart(rs));
				}
				return Optional.ofNullable(director);
			});
		} catch (DataAccessException e) {
			throw new DirectorFilmsNotFoundException(id, minReleaseDate);
		}
	}
	
	@Override
	public List<Director> selectDirectorsWithFilmsReleasedAfter(LocalDate minReleaseDate) {
		final String sql = ""
				+ "SELECT director.id, director.first_name, director.last_name, director.birth_date, "
				+ "film.name, film.release_date, film.genre "
				+ "FROM Director "
				+ "JOIN Film ON director.id = film.director_id "
				+ "WHERE release_date > ?";
		return jdbcTemplate.query(sql, new Object[] {minReleaseDate}, rs -> {
			List<Director> directorList = new ArrayList<>();
			Director currentDirector = null;
			while(rs.next()) {
				// First object
				if(currentDirector == null) {
					currentDirector = mapDirector(rs);
				} else if (currentDirector.getId() != rs.getInt("id")) {
					directorList.add(currentDirector);
					currentDirector = mapDirector(rs);
				}
				currentDirector.addFilm(FilmDataAccessService.mapFilmPart(rs));
			}
			// Last object
			if (currentDirector != null) {
				directorList.add(currentDirector);
			}
			return directorList;
		});
	}
	
	@Override
	public List<Director> selectAllDirectorsWithFilms() {
		final String sql = ""
				+ "SELECT director.id, director.first_name, director.last_name, director.birth_date, "
				+ "film.name, film.release_date, film.genre "
				+ "FROM Director "
				+ "JOIN Film ON director.id = film.director_id";
		return jdbcTemplate.query(sql, rs -> {
			List<Director> directorList = new ArrayList<>();
			Director currentDirector = null;
			while(rs.next()) {
				// First object
				if(currentDirector == null) {
					currentDirector = mapDirector(rs);
				} else if (currentDirector.getId() != rs.getInt("id")) {
					directorList.add(currentDirector);
					currentDirector = mapDirector(rs);
				}
				currentDirector.addFilm(FilmDataAccessService.mapFilmPart(rs));
			}
			// Last object
			if (currentDirector != null) {
				directorList.add(currentDirector);
			}
			return directorList;
		});
	}

	public static Director mapDirector(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String firstName = resultSet.getString("first_name");
		String lastName = resultSet.getString("last_name");
		LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
		return new Director (id, firstName, lastName, birthDate);
	}
	
}
